package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.commoon.MessagePacker
import com.dxc.ssi.agent.didcomm.model.common.Service
import com.dxc.ssi.agent.didcomm.model.didexchange.*
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.Message
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.random.Random


//TODO: Think about more generic actions constructor parameters and returns
class ReceiveInvitationAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    val connectionInitiatorController: ConnectionInitiatorController,
    private val invitationUrl: String
) : DidExchangeAction {
    override suspend fun perform(): ActionResult {
        // TODO: think to only form special message here and pass it to message processor
        // Create connection and store it in wallet // Create separate action for this?
        // Send Connection Request
        // Ensure transport is initialized?

        val invitation = parseInvitationFromInvitationUrl(invitationUrl)
        val endpoint = parseEndpointFromInvitationUrl(invitationUrl)

        println("Parsed invitation details: invitation = $invitation\nendpoint=$endpoint")

        val connectionId = uuid4().toString()


        val connection = Connection(
            id = connectionId, state = "START",
            invitation = invitationUrl,
            isSelfInitiated = true,
            peerRecipientKeys = invitation.recipientKeys,
            endpoint = endpoint
        )


        //TODO: before storing record check if there were record with the same invitation and reuse it
        //TODO: think about not storing connection object at all untill callback result is received
        walletConnector.walletHolder.storeConnectionRecord(connection)
        val callbackResult = connectionInitiatorController.onInvitationReceived(connection, endpoint, invitation)

        if (callbackResult.canProceedFurther) {

            //form request
            val connectionRequest = buildConnectionRequest(invitation, connectionId)
            val connectionRequestJson = Json.encodeToString(connectionRequest)

            println("Connection request: $connectionRequestJson")

            //send request
            //TODO: introduce message packing/unpacking here

            val messageToSend =
                MessagePacker.packAndPrepareForwardMessage(Message(connectionRequestJson), connection, walletConnector)


            //TODO: ensure that transport function is synchronous here because we will save new status to wallet only after actual message was sent
            transport.sendMessage(connection, messageToSend)

            //TODO: set proper state here
            val updatedConnection = connection.copy(state = "RequestSent")
            walletConnector.walletHolder.storeConnectionRecord(updatedConnection)

            connectionInitiatorController.onRequestSent(updatedConnection, connectionRequest)

            //update status to request sent
            return ActionResult(updatedConnection)

        } else {
            //TODO: handle this situation here
            //update status to abandoned
            val updatedConnection = connection.copy(state = "Abandoned")
            //TODO: see if we need to store it at all
            walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
            return ActionResult(updatedConnection)

        }

        //
        //val connection2 = walletConnector.walletHolder.getConnectionRecordById(connection.id)


    }

    @OptIn(InternalAPI::class)
    private fun parseInvitationFromInvitationUrl(invitationUrl: String): Invitation {
        // TODO: add validation here that invitation is proper URL
        //TODO: modify it to support other parameters apart form c_i
        val base64Invitation = Regex("^.*c_i=(.*$)").find(invitationUrl)!!.groups[1]!!.value

        println("Parsed invitation json form URL $base64Invitation")

        //TODO: find a replacement of ktor utils for decoding base64 to avoid InternalApi usage
        val jsonInvitation = base64Invitation.decodeBase64String()

        println("JSON invitation $jsonInvitation")

        return Json { ignoreUnknownKeys = true }.decodeFromString<Invitation>(jsonInvitation)

    }

    private fun parseEndpointFromInvitationUrl(invitation: String): String {
        return Regex("(^.*)\\?c_i=.*$").find(invitationUrl)!!.groups[1]!!.value
    }

    private fun buildConnectionRequest(invitation: Invitation, connectionId: String): ConnectionRequest {


        return ConnectionRequest(
            //TODO: understand how to populate id properly
            id = connectionId,
            //TODO: create enum or other holder for message type, replace hardocde and move it inside of the message, as the template will suit only this particular request
            type = "https://didcomm.org/connections/1.0/request",
            //TODO: understand what label should be
            label = "Holder",
            connection = buildConnection(),
            imageUrl = null
        )

    }

    private fun buildConnection(): com.dxc.ssi.agent.didcomm.model.didexchange.Connection {
        val connection = com.dxc.ssi.agent.didcomm.model.didexchange.Connection(
            //TODO: understand what did should be set to
            did = walletConnector.walletHolder.getIdentityDetails().did,
            didDocument = buildDidDocument()
        )
        return connection
    }

    private fun buildDidDocument(): DidDocument {

        val didDocument = DidDocument(
            //TODO: understand what context should be
            context = "https://w3id.org/did/v1",
            //TODO: understand what id should be
            id = walletConnector.walletHolder.getIdentityDetails().did,
            publicKey = buildPublicKey(),
            authentication = null, //buildAuthentication(),
            service = buildService()
        )

        return didDocument
    }

    private fun buildService(): List<Service> {
        //TODO: understand proper parameters
        //TODO: understand if indy must be present in service id
        val service = Service(
            id = "${walletConnector.walletHolder.getIdentityDetails().did};indy",
            type = "IndyAgent",
            priority = 0,
            recipientKeys = listOf(walletConnector.walletHolder.getIdentityDetails().verkey),
            //TODO: remove this randomness once agent is fixed
            serviceEndpoint = "ws://test${Random.nextInt(1000, 100000)}:8123"
        )

        return listOf(service)
    }

    private fun buildAuthentication(): List<Authentication> {
        //TODO: understand proper parameters
        val authentication = Authentication(type = "type", publicKey = "publicKey")

        return listOf(authentication)
    }

    private fun buildPublicKey(): List<PublicKey> {
        //TODO: understand proper parameters
        val publicKey =
            PublicKey(
                id = walletConnector.walletHolder.getIdentityDetails().did,
                type = "Ed25519VerificationKey2018",
                controller = walletConnector.walletHolder.getIdentityDetails().did,
                publicKeyBase58 = walletConnector.walletHolder.getIdentityDetails().verkey
            )

        return listOf(publicKey)
    }
}
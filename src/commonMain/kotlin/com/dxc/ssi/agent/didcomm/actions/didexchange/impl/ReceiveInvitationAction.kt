package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.model.common.Service
import com.dxc.ssi.agent.didcomm.model.didexchange.*
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.utils.Base64
import io.ktor.http.*
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
        val invitationUrl = Url(invitationUrl)
        val encodedInvitation = invitationUrl.parameters["c_i"]!!
        val invitation = parseInvitationFromInvitationUrl(encodedInvitation)

        val connectionId = uuid4().toString()

        val connection = PeerConnection(
            id = connectionId, state = PeerConnectionState.INVITATION_RECEIVED,
            invitation = this.invitationUrl,
            isSelfInitiated = true,
            peerRecipientKeys = invitation.recipientKeys,
            endpoint = invitation.serviceEndpoint
        )


        //TODO: before storing record check if there were record with the same invitation and reuse it
        //TODO: think about not storing connection object at all untill callback result is received
        walletConnector.walletHolder.storeConnectionRecord(connection)
        val callbackResult = connectionInitiatorController.onInvitationReceived(connection, invitation)

        if (callbackResult.canProceedFurther) {

            //form request
            val connectionRequest = buildConnectionRequest(invitation, connectionId)
            val connectionRequestJson = Json.encodeToString(connectionRequest)

            println("Connection request: $connectionRequestJson")

            //TODO: ensure that transport function is synchronous here because we will save new status to wallet only after actual message was sent
            MessageSender.packAndSendMessage(Message(connectionRequestJson), connection, walletConnector, transport)

            //TODO: set proper state here
            val updatedConnection = connection.copy(state = PeerConnectionState.REQUEST_SENT)
            walletConnector.walletHolder.storeConnectionRecord(updatedConnection)

            connectionInitiatorController.onRequestSent(updatedConnection, connectionRequest)

            //update status to request sent
            return ActionResult(updatedConnection)

        } else {
            //TODO: handle this situation here
            //update status to abandoned
            val updatedConnection = connection.copy(state = PeerConnectionState.ABANDONED)
            //TODO: see if we need to store it at all
            walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
            return ActionResult(updatedConnection)

        }


    }

    @OptIn(InternalAPI::class)
    private fun parseInvitationFromInvitationUrl(encodedInvitation: String): Invitation {
        val jsonInvitation = Base64.base64StringToPlainString(encodedInvitation)
        println("JSON invitation $jsonInvitation")
        return Json { ignoreUnknownKeys = true }.decodeFromString<Invitation>(jsonInvitation)
    }

    private fun buildConnectionRequest(invitation: Invitation, connectionId: String): ConnectionRequest {

        return ConnectionRequest(
            //TODO: understand how to populate id properly
            id = connectionId,
            //TODO: understand what label should be
            label = "Holder",
            connection = buildConnection(),
            imageUrl = null
        )

    }

    private fun buildConnection(): Connection {
        val connection = Connection(
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
package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.envelop.EncryptedEnvelop
import com.dxc.ssi.agent.didcomm.model.issue.container.Attach
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.other.Forward
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ReceiveCredentialOfferAction(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val transport: Transport,
    private val credReceiverController: CredReceiverController,
    private val credentialOfferContainerMessage: CredentialOfferContainer,
    private val connection: Connection
) : CredentialIssuenceAction {
    override fun perform(): ActionResult {

        // TODO: Check current state (if there is existing record in a wallet for previous proposals/offers)
        // TODO: Improve: This is rudimentory validation check. Just checking that there is no such record in a wallet
        val existingCredentialExchangeRecord = walletConnector.prover!!.getCredentialExchangeRecordByThread(Thread(thid = credentialOfferContainerMessage.id))
        if (existingCredentialExchangeRecord != null) throw IllegalStateException()

        //TODO: deal with more than one message in attach
        val credentialOffer = walletConnector.prover!!.buildCredentialOfferObjectFromRawData(credentialOfferContainerMessage.offersAttach[0].data)

        val credentialDefinitionId =
            walletConnector.prover!!.createCredentialDefinitionIdFromOffer(credentialOffer)

        //TODO:handle null pointer here
        val credentialDefinition = ledgerConnector.retrieveCredentialDefinition(credentialDefinitionId)!!

        if (credReceiverController.onOfferReceived(
                connection = connection,
                credentialOfferContainer = credentialOfferContainerMessage,
            ).canProceedFurther
        ) {

            val credentialRequestInfo = walletConnector.prover!!.createCredentialRequest(
                //TODO: check if it is OKay simply to take did of wallet holder always
                proverDid = walletConnector.walletHolder.getIdentityDetails().did,
                credentialDefinition = credentialDefinition,
                credentialOffer = credentialOffer,
                masterSecretId = Configuration.masterSecretId

            )

            val credentialRequest = CredentialRequestContainer(
                //TODO: create enum or other holder for message type, replace hardocde and move it inside of the message, as the template will suit only this particular request
                type = "https://didcomm.org/issue-credential/1.0/request-credential",
                //TODO: set proper id
                id = uuid4().toString(),
                thread = Thread(thid = credentialOfferContainerMessage.id),
                requestsAttach = listOf(
                    Attach(
                        id = "libindy-cred-request-0",
                        mimeType = "application/json",
                        data = walletConnector.prover!!.extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo)

                    )
                ),
                //TODO: think if we need to set some meaningful comment here
                comment = "comment"
            )

            println("Credential request created:$credentialRequest")

            // 3.2 Send CredentialRequest

            val messageEnvelop = MessageEnvelop(
                payload = walletConnector.walletHolder.packMessage(
                    Message(Json.encodeToString(credentialRequest)),
                    connection.peerRecipientKeys
                )
            )

            println("Packed message: ${messageEnvelop.payload}")

            //TODO: avoid NPE here
            val forwardMessage = buildForwardMessage(messageEnvelop, connection.peerDid!!)


            val outerMessageEnvelop = MessageEnvelop(
                payload = walletConnector.walletHolder.packMessage(
                    Message(Json.encodeToString(forwardMessage)),
                    connection.peerRecipientKeys,
                    useAnonCrypt = true
                )
            )

            transport.sendMessage(connection, outerMessageEnvelop)

            //4. Store credential record to wallet

            walletConnector.prover.storeCredentialExchangeRecord(
                CredentialExchangeRecord(
                    state = "CredentialRequestSent",
                    connectionId = connection.id,
                    //TODO: decide what data structure to store there: DiDComm generic offer or indy specific one
                    credentialOfferContainer = credentialOfferContainerMessage,
                    credentialRequestContainer = credentialRequest,
                    credentialRequestInfo = credentialRequestInfo,
                    credentialDefinition = credentialDefinition,
                    thread = Thread(thid = credentialOfferContainerMessage.id)
                )
            )

            credReceiverController.onRequestSent(connection = connection, credentialRequestContainer = credentialRequest)
        } else {
            //Else Send Problem report
            TODO()
        }


        //5. End action


        //  TODO("Not yet implemented")

        return ActionResult()
    }

    //TODO: move forward message functionality in separate package
    private fun buildForwardMessage(messageEnvelop: MessageEnvelop, inviterDid: String): Forward {

        return Forward(
            //TODO: decide where this type should be located or whether it needs to be concatenetad
            type = "https://didcomm.org/routing/1.0/forward",
            //TODO: check what the id should be
            id = "test_id",
            to = inviterDid,
            msg = Json { ignoreUnknownKeys = true }.decodeFromString<EncryptedEnvelop>(messageEnvelop.payload)
        )

    }

}

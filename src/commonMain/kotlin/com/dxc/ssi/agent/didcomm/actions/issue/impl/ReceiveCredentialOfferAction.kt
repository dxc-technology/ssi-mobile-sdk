package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.commoon.MessagePacker
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.model.messages.Message
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ReceiveCredentialOfferAction(
    private val actionParams: ActionParams
) : CredentialIssuenceAction {
    override suspend fun perform(): ActionResult {

        val walletConnector = actionParams.walletConnector
        val ledgerConnector = actionParams.ledgerConnector
        val transport = actionParams.transport
        //TODO: here and in all related places instead of NPE print relevant log message
        val credReceiverController = actionParams.callbacks.credReceiverController!!
        val credentialOfferContainerMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<CredentialOfferContainer>(actionParams.messageContext.receivedUnpackedMessage.message)
        val connection = actionParams.messageContext.connection!!


        // TODO: Check current state (if there is existing record in a wallet for previous proposals/offers)
        // TODO: Improve: This is rudimentory validation check. Just checking that there is no such record in a wallet
        val existingCredentialExchangeRecord =
            walletConnector.prover!!.getCredentialExchangeRecordByThread(Thread(thid = credentialOfferContainerMessage.id))
        if (existingCredentialExchangeRecord != null) throw IllegalStateException()

        //TODO: deal with more than one message in attach
        val credentialOffer =
            walletConnector.prover!!.buildCredentialOfferObjectFromRawData(credentialOfferContainerMessage.offersAttach[0].data)

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
                //TODO: set proper id
                id = uuid4().toString(),
                thread = Thread(thid = credentialOfferContainerMessage.id),
                requestsAttach = listOf(
                    Attach(
                        id = "libindy-cred-request-0",
                        mimeType = "application/json",
                        data = walletConnector.prover!!.extractCredentialRequestDataFromCredentialInfo(
                            credentialRequestInfo
                        )

                    )
                ),
                //TODO: think if we need to set some meaningful comment here
                comment = "comment"
            )

            println("Credential request created:$credentialRequest")

            MessageSender.packAndSendMessage(Message(Json.encodeToString(credentialRequest)), connection, walletConnector, transport)

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

            credReceiverController.onRequestSent(
                connection = connection,
                credentialRequestContainer = credentialRequest
            )
        } else {
            //Else Send Problem report
            TODO()
        }


        //5. End action


        //  TODO("Not yet implemented")

        return ActionResult()
    }

}

package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ReceiveCredentialAction(
    private val actionParams: ActionParams
) : CredentialIssuenceAction {
    override fun perform(): ActionResult {

        val walletConnector = actionParams.walletConnector
        val credReceiverController = actionParams.callbacks.credReceiverController!!
        val connection = actionParams.messageContext.connection!!

        val credentialContainerMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<CredentialContainer>(actionParams.messageContext.receivedUnpackedMessage.message)


        // 1. Check current state

        val existingCredentialExchangeRecord =
            walletConnector.prover!!.getCredentialExchangeRecordByThread(credentialContainerMessage.thread)
        if (existingCredentialExchangeRecord!!.state != "CredentialRequestSent") throw IllegalStateException()
        // 2. Execute callback
        if (credReceiverController.onCredentialReceived(
                connection = connection, credentialContainer = credentialContainerMessage
            ).canProceedFurther
        ) {

            val credential =
                walletConnector.prover!!.buildCredentialObjectFromRawData(
                    //TODO: deal with several attachemnts
                    credentialContainerMessage.credentialsAttach[0].data
                )

            // 3. Store credential
            walletConnector.prover.receiveCredential(
                credential = credential,
                credentialRequestInfo = existingCredentialExchangeRecord.credentialRequestInfo,
                credentialDefinition = existingCredentialExchangeRecord.credentialDefinition,
                //TODO: support revokation here
                revocationRegistryDefinition = null
            )
            // 4. Build Credential Ack
            //TODO: understand how to build the ack and build it (looks like .NET agent does not expect ACK though)
            // 5.  Send credential ack

            // 6. Remove credential exchange record
            walletConnector.prover!!.removeCredentialExchangeRecordByThread(credentialContainerMessage.thread)

            // 6. Execute callback
            credReceiverController.onDone(
                connection = connection, credentialContainer = credentialContainerMessage
            )
        } else {
            // TODO: Do not send ack / send problem report?
            // TODO: execute some problem related callback?
        }

        return ActionResult()
    }

}

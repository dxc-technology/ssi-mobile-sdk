package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.model.Connection

class ReceiveCredentialAction(
    private val walletConnector: WalletConnector,
    private val transport: Transport,
    private val credReceiverController: CredReceiverController,
    private val credentialContainerMessage: CredentialContainer,
    private val connection: Connection
) : CredentialIssuenceAction {
    override fun perform(): ActionResult {

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

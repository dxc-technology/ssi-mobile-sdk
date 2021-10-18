package com.dxc.ssi.agent.didcomm.actions.verify.impl

import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.verify.CredentialVerificationAction

/*
class ReceivePresentationAckAction(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val transport: Transport,
    private val credPresenterController: CredPresenterController,
    private val presentationRequestMessage: PresentationAckContainer,
    private val connection: Connection
) : CredentialVerificationAction {
    override fun perform(): ActionResult {
        TODO("Not implemented")
    }

}
*/
class ReceivePresentationAckAction : CredentialVerificationAction {
    override suspend fun perform(): ActionResult {
        return(ActionResult())
    }
}
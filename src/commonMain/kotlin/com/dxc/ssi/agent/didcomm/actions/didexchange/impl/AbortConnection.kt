package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport


//TODO: Think about more generic actions constructor parameters and returns
class AbortConnection(
    val walletConnector: WalletConnector,
    private val connectionInitiatorController: ConnectionInitiatorController,
    private val connectionId: String
) : DidExchangeAction {
    override suspend fun perform(): ActionResult {
        //TODO: think how to avoid NPE here
        val connection = walletConnector.walletHolder.getConnectionRecordById(connectionId)!!
        val updatedConnection = connection.copy(state = "Abandoned")
        walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
        //TODO: provide meaningful ProblemReport here
        val problemReport = ProblemReport(
            id = uuid4().toString(),
            description = DidCommProblemCodes.CONNECTION_ABORTED.toProblemReportDescription()
        )
        connectionInitiatorController.onAbandoned(connection, problemReport)
        return ActionResult(updatedConnection)
    }

}
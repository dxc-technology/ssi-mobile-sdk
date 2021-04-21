package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.model.didexchange.*
import com.dxc.ssi.agent.model.Connection


//TODO: Think about more generic actions constructor parameters and returns
class ReceiveConnectionResponseAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    private val connectionInitiatorController: ConnectionInitiatorController,
    private val connectionResponse: ConnectionResponse,
    private val connection: Connection
) : DidExchangeAction {
    override suspend fun perform(): ActionResult {
        connectionInitiatorController.onResponseReceived(connection,connectionResponse)
        val updatedConnection = connection.copy(state = "Complete")
        walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
        connectionInitiatorController.onCompleted(connection)
        return ActionResult(updatedConnection)
    }

}
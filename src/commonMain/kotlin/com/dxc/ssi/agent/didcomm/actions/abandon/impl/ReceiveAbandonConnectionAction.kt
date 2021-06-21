package com.dxc.ssi.agent.didcomm.actions.abandon.impl

import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.abandon.AbandonAction
import com.dxc.ssi.agent.model.PeerConnectionState

class ReceiveAbandonConnectionAction(val actionParams: ActionParams) : AbandonAction {
    override suspend fun perform(): ActionResult {

        actionParams.messageContext.connection?.let { connection ->
            val storedConnection = actionParams.walletConnector.walletHolder.getConnectionRecordById(connection.id)
            when {
                storedConnection != null -> {
                    //TODO: currently we won't handle separately case when connection is in process of establishment
                    actionParams.transport.disconnect(connection)
                    actionParams.walletConnector.walletHolder.storeConnectionRecord(connection.copy(state = PeerConnectionState.ABANDONED))
                }
                else -> {
                    // Looks like there is no such record, but just in case, try to disconnect transport
                    actionParams.transport.disconnect(connection)
                }
            }
        }

        return ActionResult()
    }

}

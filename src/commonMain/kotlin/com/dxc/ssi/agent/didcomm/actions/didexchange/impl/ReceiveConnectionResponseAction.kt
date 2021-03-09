package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.model.didexchange.*
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.MessageContext


//TODO: Think about more generic actions constructor parameters and returns
class ReceiveConnectionResponseAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    private val connectionInitiatorController: ConnectionInitiatorController,
    private val connectionResponse: ConnectionResponse,
    private val messageContext: MessageContext,
    private val connection: Connection
) : DidExchangeAction {
    override fun perform(): ActionResult {
        connectionInitiatorController.onResponseReceived(connection,connectionResponse)
        //TODO: think how to avoid NPE here
        val updatedConnection = connection.copy(state = "Complete", peerVerkey = messageContext.receivedUnpackedMessage.senderVerKey)
        walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
        connectionInitiatorController.onCompleted(connection)
        return ActionResult(updatedConnection)
    }

}
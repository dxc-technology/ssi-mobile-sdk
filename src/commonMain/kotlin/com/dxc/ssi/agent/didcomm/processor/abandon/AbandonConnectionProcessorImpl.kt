package com.dxc.ssi.agent.didcomm.processor.abandon

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.abandon.impl.InitiateAbandonConnectionAction
import com.dxc.ssi.agent.didcomm.actions.abandon.impl.ReceiveAbandonConnectionAction
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.PeerConnection


class AbandonConnectionProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    processors: Processors, services: Services
    //TODO: introduce callbacks for TrustPing
) : AbstractMessageProcessor(
    walletConnector,
    ledgerConnector,
    transport,
    callbacks,
    processors,
    services
), AbandonConnectionProcessor {

    override suspend fun abandonConnection(
        connection: PeerConnection,
        notifyPeerBeforeAbandoning: Boolean,
        problemReport: ProblemReport?
    ) {
        val abandonConnectionAction =
            InitiateAbandonConnectionAction(
                walletConnector,
                transport,
                services,
                callbacks,
                connection,
                notifyPeerBeforeAbandoning,
                problemReport
            )
        abandonConnectionAction.perform()
    }


    enum class AbandonMessageType(val _typeString: String, val _action: (ActionParams) -> Action) : MessageType {
        ABANDON_CONNECTION("^.*abandon_connection/1.0$", { actionParams ->
            ReceiveAbandonConnectionAction(actionParams)
        });

        override fun getTypeString(): String = _typeString
        override fun getMessageHandler(): (ActionParams) -> Action = _action
    }

    override fun getMessageType(message: String): MessageType {
        return getMessageTypeGeneric<AbandonMessageType>(message)
    }


}
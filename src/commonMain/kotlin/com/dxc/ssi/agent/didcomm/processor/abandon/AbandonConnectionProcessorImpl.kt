package com.dxc.ssi.agent.didcomm.processor.abandon

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.Processors
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.abandon.impl.InitiateAbandonConnectionAction
import com.dxc.ssi.agent.didcomm.actions.abandon.impl.ReceiveAbandonConnectionAction
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.PeerConnection

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor
//TODO: remove all redundant part of code left from DidExchangeProcessor
class AbandonConnectionProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    processors: Processors, trustPingTrackerService: TrustPingTrackerService
    //TODO: introduce callbacks for TrustPing
) : AbstractMessageProcessor(
    walletConnector,
    ledgerConnector,
    transport,
    callbacks,
    processors,
    trustPingTrackerService
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
                trustPingTrackerService!!,
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
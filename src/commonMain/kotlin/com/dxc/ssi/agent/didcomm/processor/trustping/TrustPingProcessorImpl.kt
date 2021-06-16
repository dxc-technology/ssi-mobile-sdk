package com.dxc.ssi.agent.didcomm.processor.trustping

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.trustping.ReceiveTrustPingResponseAction
import com.dxc.ssi.agent.didcomm.actions.trustping.SendTrustPingAction
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.processor.verify.CredVerifierProcessorImpl
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.SharedConnection

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor
//TODO: remove all redundant part of code left from DidExchangeProcessor
class TrustPingProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    trustPingProcessor: TrustPingProcessor?, trustPingTrackerService: TrustPingTrackerService
    //TODO: introduce callbacks for TrustPing
) : AbstractMessageProcessor(walletConnector, ledgerConnector, transport, callbacks, trustPingProcessor, trustPingTrackerService), TrustPingProcessor {

    override suspend fun sendTrustPingOverConnection(connection: SharedConnection): Boolean {
        val sendTrustPingAction = SendTrustPingAction(walletConnector, transport, trustPingTrackerService!!, connection)
        val actionResult = sendTrustPingAction.perform()

        return actionResult.trustPingSuccessful!!
    }



    enum class TrustPingMessageType(val _typeString: String, val _action: (ActionParams) -> Action) : MessageType {
        //TODO:  add ProblemReport/error message
        TRUST_PING_RESPONSE("^.*trust_ping/1.0/ping_response$", { actionParams ->
            ReceiveTrustPingResponseAction(actionParams)
        }),
        TRUST_PING("^.*trust_ping/1.0/ping$", { actionParams -> kotlin.TODO("Not implemented") });

        override fun getTypeString(): String = _typeString
        override fun getMessageHandler(): (ActionParams) -> Action = _action
    }

    override fun getMessageType(message: String): MessageType {
        return getMessageTypeGeneric<TrustPingMessageType>(message)
    }


}
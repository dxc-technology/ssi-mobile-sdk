package com.dxc.ssi.agent.didcomm.processor.verify

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.verify.impl.ReceivePresentationRequestAction
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationStateMachine

class CredVerifierProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    trustPingProcessor: TrustPingProcessor, trustPingTrackerService: TrustPingTrackerService
) : AbstractMessageProcessor(
    walletConnector,
    ledgerConnector,
    transport,
    callbacks,
    trustPingProcessor,
    trustPingTrackerService
), CredVerifierProcessor {

    private val stateMachine = CredentialVerificationStateMachine()

    // TODO: complete processing other message types


    enum class CredVerifyMessageType(val _typeString: String, val _action: (ActionParams) -> Action) : MessageType {
        PRESENTATION_REQUEST("^.*present-proof/1.0/request-presentation$",
            { actionParams -> ReceivePresentationRequestAction(actionParams) }),
        PRESENTATION("^.*present-proof/1.0/presentation$", { actionParams -> kotlin.TODO("Not implemented") }),
        PRESENTATION_PROPOSAL("^.*present-proof/1.0/propose-presentation$",
            { actionParams -> TODO("Not implemented") }),
        PROBLEM_REPORT("to be done", { actionParams -> kotlin.TODO("Not implemented") }),
        PRESENTATION_ACK("to be done", { actionParams -> kotlin.TODO("Not implemented") });

        override fun getTypeString(): String = _typeString
        override fun getMessageHandler(): (ActionParams) -> Action = _action
    }

    override fun getMessageType(message: String): MessageType {
        return getMessageTypeGeneric<CredVerifyMessageType>(message)
    }


}
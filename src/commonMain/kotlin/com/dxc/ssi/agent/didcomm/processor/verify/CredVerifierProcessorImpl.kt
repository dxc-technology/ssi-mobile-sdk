package com.dxc.ssi.agent.didcomm.processor.verify

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.verify.impl.ProcessPresentationRequestAction
import com.dxc.ssi.agent.didcomm.actions.verify.impl.ReceivePresentationRequestAction
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationStateMachine
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.PresentationRequestResponseAction
import com.dxc.ssi.agent.model.messages.Context

class CredVerifierProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    processors: Processors, services: Services
) : AbstractMessageProcessor(
    walletConnector,
    ledgerConnector,
    transport,
    callbacks,
    processors,
    services
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

    override suspend fun processParkedPresentationRequest(
        presentationRequestContainer: PresentationRequestContainer,
        presentationRequestResponseAction: PresentationRequestResponseAction
    ) {
        logger.d { "Preparing to process parked presentation request" }

        val actionParams = ActionParams(
            walletConnector = walletConnector,
            ledgerConnector = ledgerConnector,
            transport = transport,
            callbacks = callbacks,
            context = Context(),
            processors = processors,
            services = services
        )

        val actionResult = ProcessPresentationRequestAction(
            actionParams,
            presentationRequestContainer,
            presentationRequestResponseAction
        ).perform()
    }


}
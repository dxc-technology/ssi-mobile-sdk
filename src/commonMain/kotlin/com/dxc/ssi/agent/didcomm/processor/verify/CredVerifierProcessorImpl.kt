package com.dxc.ssi.agent.didcomm.processor.verify

import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.callbacks.verification.CredVerifierController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.verify.impl.ReceivePresentationRequestAction
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationStateMachine
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.MessageContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CredVerifierProcessorImpl(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val transport: Transport,
    private val credVerifierController: CredVerifierController?,
    private val credPresenterController: CredPresenterController?
) : CredVerifierProcessor {

    private val stateMachine = CredentialVerificationStateMachine()

    // TODO: complete processing other message types
    //TODO: see if it can be unified with DidExcange Processor
    override fun processMessage(messageContext: MessageContext) {
        //1. Determine message type and parse message
        when (getMessageType(messageContext.receivedUnpackedMessage.message)) {
            CredVerifyMessageType.PRESENTATION_REQUEST -> {
                val presentationRequestMessage =  Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<PresentationRequestContainer>(messageContext.receivedUnpackedMessage.message)

                ReceivePresentationRequestAction(
                    walletConnector, ledgerConnector,
                    //TODO: make proper check of connection here to avoid null-pointer and generate Problem Report
                    transport, credPresenterController!!, presentationRequestMessage, messageContext.connection!!
                ).perform()
            }
            CredVerifyMessageType.PRESENTATION -> {
                TODO("Not implemented")
            }
            CredVerifyMessageType.PRESENTATION_PROPOSAL -> {
                TODO("Not implemented")
            }
            CredVerifyMessageType.PRESENTATION_ACK -> {
                /*
                val presentationAckMessage =  Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<PresentationAckContainer>(messageContext.receivedUnpackedMessage.message)

                ReceivePresentationAckAction(
                    walletConnector, ledgerConnector,
                    //TODO: make proper check of connection here to avoid null-pointer and generate Problem Report
                    transport, credPresenterController!!, presentationAckMessage, messageContext.connection!!
                ).perform()
                */
                TODO("Not implemented")
            }
            CredVerifyMessageType.PROBLEM_REPORT -> {
                /*
                val presentationProblemReportMessage =  Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<PresentationProblemReportContainer>(messageContext.receivedUnpackedMessage.message)

                ReceivePresentationProblemReportAction(
                    walletConnector, ledgerConnector,
                    //TODO: make proper check of connection here to avoid null-pointer and generate Problem Report
                    transport, credPresenterController!!, presentationProblemReportMessage, messageContext.connection!!
                ).perform()
                */
                TODO("Not implemented")
            }
        }

    }

    enum class CredVerifyMessageType {
        PRESENTATION_REQUEST,
        PRESENTATION,
        PRESENTATION_PROPOSAL,
        PROBLEM_REPORT,
        PRESENTATION_ACK
    }

    private fun getMessageType(message: String): CredVerifyMessageType {

        val typeAttribute =
            Json { ignoreUnknownKeys = true }.decodeFromString<BasicMessageWithTypeOnly>(message).type

        val messageType = when {
            typeAttribute.contains("present-proof/1.0/propose-presentation") -> CredVerifyMessageType.PRESENTATION_PROPOSAL
            typeAttribute.contains("present-proof/1.0/request-presentation") -> CredVerifyMessageType.PRESENTATION_REQUEST
            typeAttribute.contains("present-proof/1.0/presentation") -> CredVerifyMessageType.PRESENTATION
            //TODO: deal with ACK and PROBLEM_REPORT messages
            /* typeAttribute.contains("connections/1.0/response") -> CredIssueMessageType.CREDENTIAL_ACK
             typeAttribute.contains("connections/1.0/response") -> CredIssueMessageType.CREDENTIAL_REJECT*/
            else -> throw IllegalArgumentException("Unknown message type: $typeAttribute")
        }

        println("Determined message type: $messageType")

        return messageType
    }
}
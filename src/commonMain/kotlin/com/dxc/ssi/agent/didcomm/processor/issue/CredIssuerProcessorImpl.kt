package com.dxc.ssi.agent.didcomm.processor.issue

import com.dxc.ssi.agent.api.callbacks.issue.CredIssuerController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.issue.impl.ReceiveCredentialAction
import com.dxc.ssi.agent.didcomm.actions.issue.impl.ReceiveCredentialOfferAction
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceStateMachine
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.MessageContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CredIssuerProcessorImpl(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val transport: Transport,
    val credIssuerController: CredIssuerController?,
    val credReceiverController: CredReceiverController?
) : CredIssuerProcessor {
    private val stateMachine = CredentialIssuenceStateMachine()

    // TODO: complete processing other message types
    //TODO: see if it can be unified with DidExcange Processor
    override fun processMessage(messageContext: MessageContext) {

        //1. Determine message type and parse message
        when (getMessageType(messageContext.receivedUnpackedMessage.message)) {
            CredIssueMessageType.CREDENTIAL_PROPOSAL -> TODO("Not implemented")
            CredIssueMessageType.CREDENTIAL_OFFER -> {
                val credentialOfferMessage =
                    Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString<CredentialOfferContainer>(messageContext.receivedUnpackedMessage.message)
                ReceiveCredentialOfferAction(
                    walletConnector, ledgerConnector,
                    //TODO: make proper check of connection here to avoid null-pointer and generate Problem Report
                    transport, credReceiverController!!, credentialOfferMessage, messageContext.connection!!
                ).perform()
            }
            CredIssueMessageType.CREDENTIAL_REQUEST -> TODO("Not implemented")
            CredIssueMessageType.CREDENTIAL -> {
                val credentialMessage =
                    Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString<CredentialContainer>(messageContext.receivedUnpackedMessage.message)
                ReceiveCredentialAction(
                    walletConnector,
                    transport, credReceiverController!!, credentialMessage, connection = messageContext.connection!!
                ).perform()
            }
            CredIssueMessageType.CREDENTIAL_ACK -> TODO("Not implemented")
            CredIssueMessageType.CREDENTIAL_REJECT -> TODO("Not implemented")
        }

    }

    enum class CredIssueMessageType {
        //TODO:  add ProblemReport/error message
        CREDENTIAL_PROPOSAL,
        CREDENTIAL_OFFER,
        CREDENTIAL_REQUEST,
        CREDENTIAL,
        CREDENTIAL_ACK,
        CREDENTIAL_REJECT
    }

    private fun getMessageType(message: String): CredIssueMessageType {

        val typeAttribute =
            Json { ignoreUnknownKeys = true }.decodeFromString<BasicMessageWithTypeOnly>(message).type

        val messageType = when {
            typeAttribute.contains("issue-credential/1.1/propose-credential") -> CredIssueMessageType.CREDENTIAL_PROPOSAL
            typeAttribute.contains("issue-credential/1.0/offer-credential") -> CredIssueMessageType.CREDENTIAL_OFFER
            typeAttribute.contains("issue-credential/1.0/request-credential") -> CredIssueMessageType.CREDENTIAL_REQUEST
            typeAttribute.contains("issue-credential/1.0/issue-credential") -> CredIssueMessageType.CREDENTIAL
            //TODO: deal with ACK and REJECT messages
            /* typeAttribute.contains("connections/1.0/response") -> CredIssueMessageType.CREDENTIAL_ACK
             typeAttribute.contains("connections/1.0/response") -> CredIssueMessageType.CREDENTIAL_REJECT*/
            else -> throw IllegalArgumentException("Unknown message type: $typeAttribute")
        }

        println("Determined message type: $messageType")

        return messageType
    }
}
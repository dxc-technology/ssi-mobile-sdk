package com.dxc.ssi.agent.didcomm.processor.issue

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.issue.impl.ReceiveCredentialAction
import com.dxc.ssi.agent.didcomm.actions.issue.impl.ReceiveCredentialOfferAction
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.processor.MessageType
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService

class CredIssuerProcessorImpl(
    walletConnector: WalletConnector,
    ledgerConnector: LedgerConnector, transport: Transport, callbacks: Callbacks,
    trustPingProcessor: TrustPingProcessor, trustPingTrackerService: TrustPingTrackerService
) : AbstractMessageProcessor(
    walletConnector, ledgerConnector, transport, callbacks, trustPingProcessor, trustPingTrackerService

), CredIssuerProcessor {


    enum class CredIssueMessageType(val _typeString: String, val _action: (ActionParams) -> Action) : MessageType {
        //TODO:  add ProblemReport/error message
        CREDENTIAL_PROPOSAL("^.*issue-credential/1.1/propose-credential$",
            { actionParams -> kotlin.TODO("Not implemented") }),
        CREDENTIAL_OFFER("^.*issue-credential/1.0/offer-credential$",
            { actionParams -> ReceiveCredentialOfferAction(actionParams) }),
        CREDENTIAL_REQUEST("^.*issue-credential/1.0/request-credential$",
            { actionParams -> kotlin.TODO("Not implemented") }),
        CREDENTIAL("^.*issue-credential/1.0/issue-credential$",
            { actionParams -> ReceiveCredentialAction(actionParams) }),
        CREDENTIAL_ACK("to be done", { actionParams -> kotlin.TODO("Not implemented") }),
        CREDENTIAL_REJECT("to be done", { actionParams -> kotlin.TODO("Not implemented") });

        override fun getTypeString(): String = _typeString
        override fun getMessageHandler(): (ActionParams) -> Action = _action
    }

    override fun getMessageType(message: String): MessageType {
        return getMessageTypeGeneric<CredIssueMessageType>(message)
    }

}
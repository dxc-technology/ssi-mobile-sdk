package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionResponderController
import com.dxc.ssi.agent.api.callbacks.issue.CredIssuerController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.callbacks.verification.CredVerifierController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.*

interface SsiAgentBuilder {
    fun build(): SsiAgentApi
    fun withTransport(transport: Transport): SsiAgentBuilder
    fun withLedgerConnector(ledgerConnector: LedgerConnector): SsiAgentBuilder
    fun withConnectionInitiatorController(connectionInitiatorController: ConnectionInitiatorController): SsiAgentBuilder
    fun withConnectionResponderController(connectionResponderController: ConnectionResponderController): SsiAgentBuilder
    fun withCredReceiverController(credReceiverController: CredReceiverController): SsiAgentBuilder
    fun withCredIssuerController(credIssuerController: CredIssuerController): SsiAgentBuilder
    fun withCredPresenterController(credPresenterController: CredPresenterController): SsiAgentBuilder
    fun withCredVerifierController(credVerifierController: CredVerifierController): SsiAgentBuilder


}
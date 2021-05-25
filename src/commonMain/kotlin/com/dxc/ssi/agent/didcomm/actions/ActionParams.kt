package com.dxc.ssi.agent.didcomm.actions

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.messages.MessageContext

data class ActionParams(
    val walletConnector: WalletConnector,
    val ledgerConnector: LedgerConnector,
    val transport: Transport,
    val callbacks: Callbacks,
    val messageContext: MessageContext,
    val trustPingProcessor: TrustPingProcessor? = null,
    val trustPingTrackerService: TrustPingTrackerService? = null
)
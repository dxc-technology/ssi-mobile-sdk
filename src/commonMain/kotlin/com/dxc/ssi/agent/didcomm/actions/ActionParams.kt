package com.dxc.ssi.agent.didcomm.actions

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.messages.Context

data class ActionParams(
    val walletConnector: WalletConnector,
    val ledgerConnector: LedgerConnector,
    val transport: Transport,
    val callbacks: Callbacks,
    val context: Context?,
    val services: Services,
    val processors: Processors
)
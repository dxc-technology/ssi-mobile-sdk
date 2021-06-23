package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState

interface SsiAgentApi {
    fun init()
    fun connect(url: String): PeerConnection

    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: PeerConnection): Boolean

    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: PeerConnection)

    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: PeerConnection)
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun shutdown(force: Boolean)
    fun getConnections(connectionState: PeerConnectionState? = PeerConnectionState.COMPLETED): Set<PeerConnection>
    fun abandonConnection(connection: PeerConnection, force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun abandonAllConnections(force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun removeAbandonedConnectionsFromWallet()
}
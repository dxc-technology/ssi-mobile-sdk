package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.PeerConnection

interface SsiAgentApi {
    fun init()
    fun connect(url: String) : PeerConnection
    fun disconnect(connection: PeerConnection)
    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: PeerConnection): Boolean
    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: PeerConnection)
    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: PeerConnection)
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun shutdown(force: Boolean)
    fun getConnections(): Set<PeerConnection>
    fun disconnect(connection: PeerConnection, force: Boolean)
    fun disconnectAll(force: Boolean)
}
package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.SharedConnection

interface SsiAgentApi {
    fun init()
    fun connect(url: String) : SharedConnection
    fun disconnect(connection: SharedConnection)
    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: SharedConnection): Boolean
    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: SharedConnection)
    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: SharedConnection)
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun shutdown(force: Boolean)
    fun getConnections(): Set<SharedConnection>
    fun disconnect(connection: SharedConnection, force: Boolean)
    fun disconnectAll(force: Boolean)
}
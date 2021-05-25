package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.Connection

interface SsiAgentApi {
    fun init()
    fun connect(url: String) : Connection
    fun disconnect(connection: Connection)
    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: Connection): Boolean
    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: Connection)
    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: Connection)
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun shutdown(force: Boolean)
    fun getConnections(): Set<Connection>
    fun disconnect(connection: Connection, force: Boolean)
    fun disconnectAll(force: Boolean)
}
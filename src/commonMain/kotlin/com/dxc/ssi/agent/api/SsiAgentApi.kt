package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState

interface SsiAgentApi {
    fun init()
    fun connect(url: String, keepConnectionAlive: Boolean = false): PeerConnection
    fun reconnect(connection: PeerConnection, keepConnectionAlive: Boolean = false)
    fun keepConnectionAlive(connection:PeerConnection, keepConnectionAlive: Boolean = true)
    fun disconnect(connection:PeerConnection)

    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: PeerConnection): Boolean

    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: PeerConnection)

    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: PeerConnection)
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun getTransport(): Transport
    fun shutdown(force: Boolean)
    fun getConnection(connectionId: String): PeerConnection?
    fun getConnections(connectionState: PeerConnectionState? = PeerConnectionState.COMPLETED): Set<PeerConnection>
    fun abandonConnection(connection: PeerConnection, force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun abandonAllConnections(force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun removeAbandonedConnectionsFromWallet()

    fun getParkedCredentialOffers(): Set<CredentialOfferContainer>
    fun processParkedCredentialOffer(credentialOfferContainer: CredentialOfferContainer,  offerResponseAction: OfferResponseAction)
}
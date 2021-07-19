package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.verify.data.CredentialInfo
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState

interface SsiAgentApi {
    // Library wide actions
    fun init()
    fun shutdown(force: Boolean)

    //Get pluggable implementations
    fun getLedgerConnector(): LedgerConnector
    fun getWalletConnector(): WalletConnector
    fun getTransport(): Transport

    // Connection management
    fun connect(url: String, keepConnectionAlive: Boolean = false): PeerConnection
    fun reconnect(connection: PeerConnection, keepConnectionAlive: Boolean = false)
    fun keepConnectionAlive(connection: PeerConnection, keepConnectionAlive: Boolean = true)
    fun disconnect(connection: PeerConnection)
    fun getConnection(connectionId: String): PeerConnection?
    fun getConnections(connectionState: PeerConnectionState? = PeerConnectionState.COMPLETED): Set<PeerConnection>
    fun abandonConnection(connection: PeerConnection, force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun abandonAllConnections(force: Boolean = true, notifyPeerBeforeAbandoning: Boolean = true)
    fun removeAbandonedConnectionsFromWallet()

    // Credentials management
    fun getParkedCredentialOffers(): Set<CredentialOfferContainer>
    fun processParkedCredentialOffer(
        credentialOfferContainer: CredentialOfferContainer,
        offerResponseAction: OfferResponseAction
    )

    fun getCredentialInfos(): Set<CredentialInfo>
    fun getCredentialInfo(localWalletCredId: String): CredentialInfo

    //TODO: understand proper signature for this function
    fun issueCredentialOverConnection(connection: PeerConnection)

    // uncategorized

    //TODO: add timeout and sync/async modifier
    fun sendTrustPing(connection: PeerConnection): Boolean

    //TODO: understand proper signature for this function
    fun requestProofOverConnection(connection: PeerConnection)


}
package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.listener.MessageListener
import com.dxc.ssi.agent.didcomm.listener.MessageListenerImpl
import com.dxc.ssi.agent.model.Connection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks
) : SsiAgentApi {
    private val messageListener: MessageListener =
        MessageListenerImpl(transport, walletConnector, ledgerConnector, callbacks)

    //TODO: add callback controllers here


    override fun init() {
        walletConnector.walletHolder.openOrCreateWallet()
        ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did

        if (walletConnector.prover != null) {
            walletConnector.prover!!.createMasterSecret(Configuration.masterSecretId)
        }

//TODO: design proper concurrency there
        GlobalScope.launch { messageListener.listen() }

    }

    override fun connect(url: String): Connection {
        return messageListener.messageRouter.didExchangeProcessor.initiateConnectionByInvitation(url)
    }

    override fun disconnect(connection: Connection) {
        TODO("Not yet implemented")
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: Connection): Boolean {
        return messageListener.messageRouter.trustPingProcessor.sendTrustPingOverConnection(connection)
    }

    override fun issueCredentialOverConnection(connection: Connection) {
        TODO("Not yet implemented")
    }

    override fun requestProofOverConnection(connection: Connection) {
        TODO("Not yet implemented")
    }

    override fun getLedgerConnector(): LedgerConnector {
        TODO("Not yet implemented")
    }

    override fun getWalletConnector(): WalletConnector {
        TODO("Not yet implemented")
    }

    override fun shutdown(force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getConnections(): Set<Connection> {
        TODO("Not yet implemented")
    }

    override fun disconnect(connection: Connection, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun disconnectAll(force: Boolean) {
        TODO("Not yet implemented")
    }
}
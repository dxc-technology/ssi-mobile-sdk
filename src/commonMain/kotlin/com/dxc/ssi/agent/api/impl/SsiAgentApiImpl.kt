package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.listener.MessageListener
import com.dxc.ssi.agent.didcomm.listener.MessageListenerImpl
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.utils.PlatformInit
import com.dxc.ssi.agent.utils.Waiter
import kotlinx.coroutines.*

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks
) : SsiAgentApi {
    private val messageListener: MessageListener = MessageListenerImpl(transport, walletConnector, callbacks)

    //TODO: add callback controllers here


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun init() {

        val platformInit = PlatformInit()
        platformInit.init()

        Waiter.waitForCompletion(GlobalScope.async {
            walletConnector.walletHolder.openOrCreateWallet()
        })


//TODO: design proper concurrency there
        GlobalScope.launch {
            //TODO: understannd for which functions we need to use separate thread, for which Dispathers.Default and for which Dispatchers.IO
            withContext(newSingleThreadContext("Listener thread")) {
                messageListener.listen()
            }
        }


    }

    override fun connect(url: String): Connection {
        return Waiter.waitForCompletion(
            GlobalScope.async {
                messageListener.messageRouter.didExchangeProcessor.initiateConnectionByInvitation(url)
            })
    }

    override fun disconnect(connection: Connection) {
        TODO("Not yet implemented")
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: Connection): Boolean {
        return Waiter.waitForCompletion(
            GlobalScope.async {
                messageListener.messageRouter.trustPingProcessor.sendTrustPingOverConnection(connection)
            })
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
package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.listener.MessageListener
import com.dxc.ssi.agent.didcomm.listener.MessageListenerImpl
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.utils.PlatformInit
import com.dxc.ssi.agent.utils.CoroutineHelper
import kotlinx.coroutines.*

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks
) : SsiAgentApi {
    private val trustPingTrackerService =
        TrustPingTrackerService(walletConnector, callbacks.connectionInitiatorController!!)

    private val messageListener: MessageListener =
        MessageListenerImpl(transport, walletConnector, ledgerConnector, trustPingTrackerService, callbacks)


    //TODO: add callback controllers here


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun init() {

        val platformInit = PlatformInit()
        platformInit.init()

        CoroutineHelper.waitForCompletion(GlobalScope.async {
            ledgerConnector.init()
            walletConnector.walletHolder.openOrCreateWallet()
            //TODO: lloks like here we need to make ledgerCOnnector.did to be IsoSatte
            ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did

            if (walletConnector.prover != null) {
                walletConnector.prover!!.createMasterSecret(Configuration.masterSecretId)
            }
        })

//TODO: design proper concurrency there
        GlobalScope.launch {
            //TODO: understannd for which functions we need to use separate thread, for which Dispathers.Default and for which Dispatchers.IO
            withContext(CoroutineHelper.singleThreadCoroutineContext("Listener thread")) {
                messageListener.listen()
            }
        }
//TODO: design proper concurrency there
        GlobalScope.launch {
            //TODO: understannd for which functions we need to use separate thread, for which Dispathers.Default and for which Dispatchers.IO
            withContext(CoroutineHelper.singleThreadCoroutineContext("Listener thread")) {
                trustPingTrackerService.track()
            }
        }

    }

    override fun connect(url: String): Connection {
        return CoroutineHelper.waitForCompletion(
            GlobalScope.async {
                messageListener.messageRouter.didExchangeProcessor.initiateConnectionByInvitation(url)
            })
    }

    override fun disconnect(connection: Connection) {
        TODO("Not yet implemented")
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: Connection): Boolean {
        return CoroutineHelper.waitForCompletion(
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
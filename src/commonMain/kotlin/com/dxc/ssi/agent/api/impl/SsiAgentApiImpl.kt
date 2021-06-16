package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.Environment
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.listener.MessageListener
import com.dxc.ssi.agent.didcomm.listener.MessageListenerImpl
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.utils.EnvironmentUtils
import kotlinx.coroutines.*

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks,
    private val environment:Environment
) : SsiAgentApi {
    private val trustPingTrackerService =
        TrustPingTrackerService(walletConnector, callbacks.connectionInitiatorController!!)

    private val messageListener: MessageListener =
        MessageListenerImpl(transport, walletConnector, ledgerConnector, trustPingTrackerService, callbacks)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun init() {

        EnvironmentUtils.initEnvironment(environment)


        CoroutineHelper.waitForCompletion(GlobalScope.async {
            println("Before initializing ledgerConnector")
            ledgerConnector.init()
            println("After initializing ledgerConnector")
            println("Before initializing walletConnector")
            walletConnector.walletHolder.openOrCreateWallet()
            println("After initializing walletConnector")
            ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did
            println("Set ledgerConnectorDid")

            if (walletConnector.prover != null) {
                walletConnector.prover!!.createMasterSecret(Configuration.masterSecretId)
            }
        })

        println("Before running listener in GlobalScope")
//TODO: design proper concurrency there,  think if GlobalScope is appropriate here or some custom job should be used
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

    override fun connect(url: String): SharedConnection {
        return CoroutineHelper.waitForCompletion(
            GlobalScope.async {
                messageListener.messageRouter.didExchangeProcessor.initiateConnectionByInvitation(url)
            })
    }

    override fun disconnect(connection: SharedConnection) {
        TODO("Not yet implemented")
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: SharedConnection): Boolean {
        return CoroutineHelper.waitForCompletion(
            GlobalScope.async {
                messageListener.messageRouter.trustPingProcessor.sendTrustPingOverConnection(connection)
            })
    }

    override fun issueCredentialOverConnection(connection: SharedConnection) {
        TODO("Not yet implemented")
    }

    override fun requestProofOverConnection(connection: SharedConnection) {
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

    override fun getConnections(): Set<SharedConnection> {
        TODO("Not yet implemented")
    }

    override fun disconnect(connection: SharedConnection, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun disconnectAll(force: Boolean) {
        TODO("Not yet implemented")
    }
}
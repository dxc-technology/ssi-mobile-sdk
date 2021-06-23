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
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.utils.EnvironmentUtils
import kotlinx.coroutines.*

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks
) : SsiAgentApi {

    private var job = Job()
    private val agentScope = CoroutineScope(Dispatchers.Default + job)

    /*TODO: for mobile application having one thread of listener is enough.
    for mobile application having one thread of listener is enough.For using on server side we will need to implement Thread Pool ourselves, or wait until it is done in kotlin coroutines
    The problem is  with IOS Dispatchers.Default having very limited number of threads.
    Alternative solution to creating our thread pool will be separation of this listener invokation to platform specific implementations.
    Then for JVM we will use standard thread pool, while for ios it wil be enough to have single listener thread
     */

    private val mainListenerSingleThreadDispatcher =
        CoroutineHelper.singleThreadCoroutineContext("Main Listener Thread")
    private val trustPingListenerSingleThreadDispatcher =
        CoroutineHelper.singleThreadCoroutineContext("TrustPing Listener Thread")
    private val trustPingTrackerService =
        TrustPingTrackerService(walletConnector, callbacks.connectionInitiatorController!!)

    private val messageListener: MessageListener =
        MessageListenerImpl(transport, walletConnector, ledgerConnector, trustPingTrackerService, callbacks)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun init() {

        if (!EnvironmentUtils.environmentInitizlized)
            throw RuntimeException("Please initialize environment before initializing SsiAgentApiImpl")


        println("Before running agentScope.async")
        CoroutineHelper.waitForCompletion(agentScope.async {
            println("Before initializing ledgerConnector")
            ledgerConnector.init()
            //TODO: combine it into single function
            walletConnector.walletHolder.openWalletOrFail()
            walletConnector.walletHolder.initializeDid()
            ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did
            println("Set ledgerConnectorDid")

            if (walletConnector.prover != null) {
                walletConnector.prover.createMasterSecret(Configuration.masterSecretId)
            }
        })

        agentScope.launch {
            withContext(mainListenerSingleThreadDispatcher.context) {
                messageListener.listen()
            }
        }

        agentScope.launch {
            withContext(trustPingListenerSingleThreadDispatcher.context) {
                trustPingTrackerService.track()
            }
        }
    }

    override fun connect(url: String): PeerConnection {
        println("Entered connect function")
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                println("Entered async connection initiation")
                //TODO: fix NPE
                messageListener.messageRouter.processors.didExchangeProcessor!!.initiateConnectionByInvitation(url)
            })
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: PeerConnection): Boolean {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                //TODO: fix NPE
                messageListener.messageRouter.processors.trustPingProcessor!!.sendTrustPingOverConnection(connection)
            })
    }

    override fun issueCredentialOverConnection(connection: PeerConnection) {
        TODO("Not yet implemented")
    }

    override fun requestProofOverConnection(connection: PeerConnection) {
        TODO("Not yet implemented")
    }

    override fun getLedgerConnector(): LedgerConnector {
        TODO("Not yet implemented")
    }

    override fun getWalletConnector(): WalletConnector {
        TODO("Not yet implemented")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun shutdown(force: Boolean) {
        //TODO: make some intelligence and control cancellation behaviour. Make cancellation graceful and controllable. Understand what force parameter would mean
        job.cancel()
        mainListenerSingleThreadDispatcher.closeContext()
        trustPingListenerSingleThreadDispatcher.closeContext()
        transport.shutdown()
        println("Stopped the agent")
    }

    override fun getConnections(includingAbandoned: Boolean): Set<PeerConnection> {
        TODO("Not yet implemented")
    }

    override fun abandonConnection(connection: PeerConnection, force: Boolean, notifyPeerBeforeAbandoning: Boolean) {
        CoroutineHelper.waitForCompletion(
            agentScope.async {
                //TODO: fix NPE
                messageListener.messageRouter.processors.abandonConnectionProcessor!!.abandonConnection(
                    connection,
                    notifyPeerBeforeAbandoning
                )
            })
    }

    override fun abandonAllConnections(force: Boolean, notifyPeerBeforeAbandoning: Boolean) {
        getConnections().forEach { abandonConnection(it, force, notifyPeerBeforeAbandoning) }
    }

    override fun removeAbandonedConnectionsFromWallet() {
        TODO("Not yet implemented")
        /*
        * Currently when we disconnect a connection , we mark it as Abandoned in wallet. This function is to cleanup such connections
        * */
    }
}
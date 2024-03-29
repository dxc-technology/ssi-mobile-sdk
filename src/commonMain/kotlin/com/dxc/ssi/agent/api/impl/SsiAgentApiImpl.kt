package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.callbacks.library.LibraryError
import com.dxc.ssi.agent.api.callbacks.library.LibraryStateListener
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.listener.MessageListener
import com.dxc.ssi.agent.didcomm.listener.MessageListenerImpl
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.model.verify.data.CredentialInfo
import com.dxc.ssi.agent.didcomm.services.ConnectionsTrackerService
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.model.PresentationRequestResponseAction
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.utils.EnvironmentUtils
import kotlinx.coroutines.*

class SsiAgentApiImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val callbacks: Callbacks
) : SsiAgentApi {

    private var logger: Kermit = Kermit(LogcatLogger())
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


    private val services = Services()

    private val messageListener: MessageListener =
        MessageListenerImpl(transport, walletConnector, ledgerConnector, services, callbacks)


    init {

        services.connectionsTrackerService = ConnectionsTrackerService(
            walletConnector,
            callbacks,
            messageListener.messageRouter.processors
        )


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun init(libraryStateListener: LibraryStateListener) {

        if (!EnvironmentUtils.environmentInitizlized)
            throw RuntimeException("Please initialize environment before initializing SsiAgentApiImpl")

        logger.d { "Before running agentScope.async" }
        agentScope.launch {
            try {
                logger.d { "Before initializing ledgerConnector" }
                ledgerConnector.init()
            } catch (t: Throwable) {
                agentScope.launch {
                    libraryStateListener.initializationFailed(
                        error = LibraryError.LEDGER_CONNECTION_EXCEPTION,
                        stackTrace = t.stackTraceToString()
                    )
                }
                return@launch

            }
            try {
                //TODO: combine it into single function
                walletConnector.walletHolder.openWalletOrFail()
                walletConnector.walletHolder.initializeDid()
                ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did
                logger.d { "Set ledgerConnectorDid" }

                if (walletConnector.prover != null) {
                    walletConnector.prover.createMasterSecret(Configuration.masterSecretId)
                }
            } catch (t: Throwable) {

                agentScope.launch {
                    libraryStateListener.initializationFailed(
                        error = LibraryError.WALLET_INITIALIZATION_EXCEPTION,
                        stackTrace = t.stackTraceToString()
                    )
                }
                return@launch

            }
            try {

                logger.d { "init: initialized ledger and wallet" }

                agentScope.launch {
                    withContext(mainListenerSingleThreadDispatcher.context) {
                        messageListener.listen()
                    }
                }

                logger.d { "init: initialized message listener" }

                agentScope.launch {
                    withContext(trustPingListenerSingleThreadDispatcher.context) {
                        services.connectionsTrackerService!!.start()
                    }
                }
                logger.d { "init: initialized trust ping tracker service" }

                agentScope.launch { libraryStateListener.initializationCompleted() }

            } catch (t: Throwable) {
                agentScope.launch {
                    libraryStateListener.initializationFailed(
                        error = LibraryError.LISTENER_SETUP_EXCEPTION,
                        stackTrace = t.stackTraceToString()
                    )
                }

            }

        }

    }

    override fun connect(url: String, keepConnectionAlive: Boolean): PeerConnection? {
        logger.d { "Entered connect function" }
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                logger.d { "Entered async connection initiation" }
                //TODO: fix NPE
                messageListener.messageRouter.processors.didExchangeProcessor!!.initiateConnectionByInvitation(
                    url,
                    keepConnectionAlive
                )
            })
    }

    override fun reconnect(connection: PeerConnection, keepConnectionAlive: Boolean) {
        try {
            CoroutineHelper.waitForCompletion(
                agentScope.async {
                    logger.d { "Entered async keepAlive connection status change" }
                    //TODO: think about avoiding NPE
                    services.connectionsTrackerService!!.reconnect(connection, keepConnectionAlive)

                })
        } catch (t: Throwable) {
            logger.e(
                "Error in reconnect inside library with $connection",
                t
            ) { t.message.toString() }
        }
    }

    override fun keepConnectionAlive(connection: PeerConnection, keepConnectionAlive: Boolean) {
        try {
            CoroutineHelper.waitForCompletion(
                agentScope.async {
                    logger.d { "Entered async keepAlive connection status change" }
                    //TODO: think about avoiding NPE
                    services.connectionsTrackerService!!.keepConnectionAlive(
                        connection,
                        keepConnectionAlive
                    )

                })
        } catch (t: Throwable) {
            logger.e(
                "Error in keepConnectionAlive inside library",
                t
            ) { t.message.toString() }
        }
    }

    override fun disconnect(connection: PeerConnection) {
        try {
            CoroutineHelper.waitForCompletion(
                agentScope.async {
                    logger.d { "Entered async disconnect" }
                    services.connectionsTrackerService!!.keepConnectionAlive(connection, false)
                    transport.disconnect(connection)

                })
        } catch (t: Throwable) {
            logger.e(
                "Error in disconnect inside library with $connection",
                t
            ) { t.message.toString() }
        }
    }

    //TODO: current function is synchronous with hardcoded timeout, generalize it
    override fun sendTrustPing(connection: PeerConnection): Boolean {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                //TODO: fix NPE
                messageListener.messageRouter.processors.trustPingProcessor!!.sendTrustPingOverConnection(
                    connection
                )
            })

    }

    override fun issueCredentialOverConnection(connection: PeerConnection) {
        TODO("Not yet implemented")
    }

    override fun requestProofOverConnection(connection: PeerConnection) {
        TODO("Not yet implemented")
    }

    override fun getLedgerConnector(): LedgerConnector = ledgerConnector
    override fun getWalletConnector(): WalletConnector = walletConnector
    override fun getTransport(): Transport = transport


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun shutdown(force: Boolean) {
        //TODO: make some intelligence and control cancellation behaviour. Make cancellation graceful and controllable. Understand what force parameter would mean
        try {
            job.cancel()
            mainListenerSingleThreadDispatcher.closeContext()
            trustPingListenerSingleThreadDispatcher.closeContext()
            services.connectionsTrackerService!!.shutdown()
            transport.shutdown()
            logger.d { "Stopped the agent" }
        } catch (t: Throwable) {
            logger.e("Error in shutdown inside library", t) { t.message.toString() }
        }
    }

    override fun getConnection(connectionId: String): PeerConnection? {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.walletHolder.getConnectionRecordById(connectionId)
            })
    }

    override fun getConnections(connectionState: PeerConnectionState?): Set<PeerConnection> {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.walletHolder.getConnections(connectionState)
            })

    }

    override fun abandonConnection(
        connection: PeerConnection,
        force: Boolean,
        notifyPeerBeforeAbandoning: Boolean
    ) {
        try {
            CoroutineHelper.waitForCompletion(
                agentScope.async {
                    //TODO: fix NPE
                    messageListener.messageRouter.processors.abandonConnectionProcessor!!.abandonConnection(
                        connection,
                        notifyPeerBeforeAbandoning
                    )
                })
        } catch (t: Throwable) {
            logger.e(
                "Error in abandonConnection inside library with $connection",
                t
            ) { t.message.toString() }
        }
    }

    override fun abandonAllConnections(force: Boolean, notifyPeerBeforeAbandoning: Boolean) {
        try {
            getConnections().forEach { abandonConnection(it, force, notifyPeerBeforeAbandoning) }
        } catch (t: Throwable) {
            logger.e(
                "Error in abandonAllConnections inside library",
                t
            ) { t.message.toString() }
        }
    }

    override fun removeAbandonedConnectionsFromWallet() {
        TODO("Not yet implemented")
        /*
        * Currently when we disconnect a connection , we mark it as Abandoned in wallet. This function is to cleanup such connections
        * */
    }

    override fun getParkedCredentialOffers(): Set<CredentialOfferContainer> {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.prover!!.getParkedCredentialOffers()
            })
    }

    override fun processParkedCredentialOffer(
        credentialOfferContainer: CredentialOfferContainer,
        offerResponseAction: OfferResponseAction
    ) {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                //TODO: fix NPE
                messageListener.messageRouter.processors.credIssuerProcessor!!.processParkedCredentialOffer(
                    credentialOfferContainer,
                    offerResponseAction
                )
            })
    }

    override fun getParkedPresentationRequests(): Set<PresentationRequestContainer> {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.prover!!.getParkedPresentationRequests()
            })

    }

    override fun processParkedPresentationRequest(
        presentationRequestContainer: PresentationRequestContainer,
        presentationRequestResponseAction: PresentationRequestResponseAction
    ) {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                //TODO: fix NPE
                messageListener.messageRouter.processors.credVerifierProcessor!!.processParkedPresentationRequest(
                    presentationRequestContainer,
                    presentationRequestResponseAction
                )
            })
    }

    override fun getCredentialInfos(): Set<CredentialInfo> {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.prover!!.getCredentialInfos()
            })
    }

    override fun getCredentialInfo(localWalletCredId: String): CredentialInfo {
        return CoroutineHelper.waitForCompletion(
            agentScope.async {
                walletConnector.prover!!.getCredentialInfo(localWalletCredId)
            })
    }
}
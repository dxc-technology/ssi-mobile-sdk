package com.dxc.ssi.sample

import android.app.Application
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.callbacks.library.LibraryError
import com.dxc.ssi.agent.api.callbacks.library.LibraryStateListener
import com.dxc.ssi.agent.api.impl.EnvironmentImpl
import com.dxc.ssi.agent.api.impl.SsiAgentBuilderImpl
import com.dxc.ssi.agent.api.pluggable.wallet.WalletManager
import com.dxc.ssi.agent.api.pluggable.wallet.indy.IndyWalletConnector
import com.dxc.ssi.agent.ledger.indy.GenesisMode
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorBuilder
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.wallet.indy.IndyWalletHolder
import com.dxc.ssi.agent.wallet.indy.IndyWalletManager
import com.dxc.ssi.sample.controllers.ConnectionInitiatorControllerImpl
import com.dxc.ssi.sample.controllers.CredPresenterControllerImpl
import com.dxc.ssi.sample.controllers.CredReceiverControllerImpl
import com.dxc.utils.EnvironmentUtils
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity

var ssiAgentApi: SsiAgentApi? = null

class SsiApplication : Application() {

    private var agentInitialized: Boolean = false
    private var agentInitializationInProgress = false
    private val walletName = "newWalletName2"
    private val walletPassword = "newWalletPassword"
    private val did = "Kg5Cq9vKv7QrLfTGUP9xbd"
    var logger: Kermit = Kermit(LogcatLogger())

    override fun onCreate() {
        super.onCreate()
        logger.d { "Created SSI Application" }

        if (PermissionManager.getMissingPermissions(PermissionManager.requiredPermissions, this).isEmpty()) {
            initSsiAgent()
        }
    }

    fun getSsiAgentIfInitialized(): SsiAgentApi? {
        return if (agentInitialized)
            ssiAgentApi!!
        else if(agentInitializationInProgress == false) {
            initSsiAgent()
            null
        }
        else {
            null
        }

    }

    private fun initSsiAgent() {
        agentInitializationInProgress = true

        EnvironmentUtils.initEnvironment(EnvironmentImpl(this))

        val walletManager: WalletManager = IndyWalletManager

        if (!walletManager.isWalletExistsAndOpenable(walletName, walletPassword))
            walletManager.createWallet(walletName, walletPassword)

        if (!walletManager.isDidExistsInWallet(did, walletName, walletPassword)) {
            val didResult = walletManager.createDid(
                didConfig = DidConfig(did = did),
                walletName = walletName,
                walletPassword = walletPassword
            )
            logger.d { "Generated didResult: $didResult" }
            //Store did somewhere in your application to use it afterwards
        }

        val walletHolder = IndyWalletHolder(
            walletName = walletName,
            walletPassword = walletPassword,
            didConfig = DidConfig(did = did)
        )

        val indyWalletConnector = IndyWalletConnector.build(walletHolder)

        val indyLedgerConnector = IndyLedgerConnectorBuilder()
            .withGenesisMode(GenesisMode.SOVRIN_BUILDERNET)
            .build()

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(indyLedgerConnector)
            .build()

        ssiAgentApi!!.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                agentInitialized = true
                agentInitializationInProgress = true
                logger.d { "Initialized SSI Agent" }

            }

            override fun initializationFailed(
                error: LibraryError,
                message: String?,
                details: String?,
                stackTrace: String?
            ) {
                agentInitializationInProgress = true
                logger.d {
                    "Failure to initialize library:" +
                            "error -> $error" +
                            "message -> $message" +
                            "details -> $details" +
                            "stackTrace -> $stackTrace"
                }
            }


        }
        )


    }


}
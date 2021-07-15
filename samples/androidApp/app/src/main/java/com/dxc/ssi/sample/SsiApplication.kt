package com.dxc.ssi.sample

import android.app.Application
import android.os.Environment
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.impl.EnvironmentImpl
import com.dxc.ssi.agent.api.impl.SsiAgentBuilderImpl
import com.dxc.ssi.agent.api.pluggable.wallet.WalletManager
import com.dxc.ssi.agent.api.pluggable.wallet.indy.IndyWalletConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.wallet.indy.IndyWalletHolder
import com.dxc.ssi.agent.wallet.indy.IndyWalletManager
import com.dxc.ssi.sample.controllers.ConnectionInitiatorControllerImpl
import com.dxc.ssi.sample.controllers.CredPresenterControllerImpl
import com.dxc.ssi.sample.controllers.CredReceiverControllerImpl
import com.dxc.utils.EnvironmentUtils

var ssiAgentApi: SsiAgentApi? = null
class SsiApplication : Application() {

    private var agentInitialized: Boolean = false
    private val walletName = "newWalletName2"
    private val walletPassword = "newWalletPassword"
    private val did = "Kg5Cq9vKv7QrLfTGUP9xbd"



    override fun onCreate() {
        super.onCreate()
        println("Created SSI Application")

        if (PermissionManager.getMissingPermissions(PermissionManager.requiredPermissions, this).isEmpty()) {
            initSsiAgent()
        }
    }

    fun getSsiAgent(): SsiAgentApi {
        if (agentInitialized)
            return ssiAgentApi!!

        initSsiAgent()
        return ssiAgentApi!!
    }

    private fun initSsiAgent() {

        EnvironmentUtils.initEnvironment(EnvironmentImpl(this))

        val walletManager: WalletManager = IndyWalletManager

        if (!walletManager.isWalletExistsAndOpenable(walletName, walletPassword))
            walletManager.createWallet(walletName, walletPassword)

        if (!walletManager.isDidExistsInWallet(did, walletName, walletPassword)) {
            val didResult = walletManager.createDid(didConfig = DidConfig(did = did),walletName = walletName, walletPassword = walletPassword)
            println("Generated didResult: $didResult")
            //Store did somewhere in your application to use it afterwards
        }

        val walletHolder = IndyWalletHolder(
            walletName = walletName,
            walletPassword = walletPassword,
            didConfig = DidConfig(did = did)
        )

        val indyWalletConnector = IndyWalletConnector.build(walletHolder)

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.SOVRIN_BUILDERNET)

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(IndyLedgerConnector(indyLedgerConnectorConfiguration))
            .build()

        ssiAgentApi!!.init()

        agentInitialized = true
        println("Initialized SSI Agent")
    }


}
package com.dxc.ssi.sample

import android.app.Application
import android.os.Environment
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.impl.EnvironmentImpl
import com.dxc.ssi.agent.api.impl.SsiAgentBuilderImpl
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.sample.controllers.ConnectionInitiatorControllerImpl
import com.dxc.ssi.sample.controllers.CredPresenterControllerImpl
import com.dxc.ssi.sample.controllers.CredReceiverControllerImpl

class SsiApplication : Application() {

    private var agentInitialized: Boolean = false


    private lateinit var ssiAgentApi: SsiAgentApi

    override fun onCreate() {
        super.onCreate()
        println("Created SSI Application")

        if (PermissionManager.getMissingPermissions(PermissionManager.requiredPermissions, this).isEmpty()) {
            initSsiAgent()
        }
    }

    fun getSsiAgent(): SsiAgentApi {
        if (agentInitialized)
            return ssiAgentApi

        initSsiAgent()
        return ssiAgentApi
    }

    private fun initSsiAgent() {

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
            ipAddress = "192.168.0.117")

        ssiAgentApi = SsiAgentBuilderImpl()
            .withEnvironment(EnvironmentImpl(this))
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(IndyLedgerConnector(indyLedgerConnectorConfiguration))
            .build()

        ssiAgentApi.init()

        agentInitialized = true
        println("Initialized SSI Agent")
    }


}
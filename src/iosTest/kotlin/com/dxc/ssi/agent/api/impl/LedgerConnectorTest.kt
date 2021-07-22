package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.ledger.indy.GenesisMode
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.utils.ToBeReworked
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.Sleeper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.test.Ignore
import kotlin.test.Test

class LedgerConnectorTest {

    @Test
    @Ignore
    fun testConnectionToPool() {
        ToBeReworked.enableIndyLog()

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = GenesisMode.IP,
            ipAddress = "192.168.0.117"
        )


        GlobalScope.launch {
            withContext(Dispatchers.Default) {
            PoolHelper.openOrCreateCustomGenesis(
                GenesisMode.IP,
                indyLedgerConnectorConfiguration.ipAddress,
                EnvironmentUtils.indyHomePath
            ) }
        }

        Sleeper().sleep(100000)
    }
}
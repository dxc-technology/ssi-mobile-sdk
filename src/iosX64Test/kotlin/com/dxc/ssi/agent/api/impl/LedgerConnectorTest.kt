package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.utils.ToBeReworked
import com.dxc.utils.Sleeper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.Foundation.NSHomeDirectory
import kotlin.test.Test

class LedgerConnectorTest {

    @Test
    fun testConnectionToPool() {
        ToBeReworked.enableIndyLog()

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
            ipAddress = "192.168.0.117",
            dirForGeneratedGenesis = NSHomeDirectory()
        )


        GlobalScope.launch {
            withContext(Dispatchers.Default) {
            PoolHelper.openOrCreateFromIp(
                indyLedgerConnectorConfiguration.ipAddress,
                indyLedgerConnectorConfiguration.dirForGeneratedGenesis
            ) }
        }

        Sleeper().sleep(100000)
    }
}
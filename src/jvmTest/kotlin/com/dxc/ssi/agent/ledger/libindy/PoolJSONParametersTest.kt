package com.dxc.ssi.agent.ledger.libindy

import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.pool.PoolJSONParameters
import org.junit.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString

class PoolJSONParametersTest {

    @Test
    fun testCreatePoolLedgerConfigJSONParameter() {

        val genesisTxn = "/Users/ifedyanin/tmp/genesys_file_name.txn"

        val expectedValue = PoolJSONParameters.CreatePoolLedgerConfigJSONParameter(genesisTxn).toJson()
        val actualValue = Json {  }.encodeToString (com.dxc.ssi.agent.ledger.indy.libindy.PoolJSONParameters.CreatePoolLedgerConfigJSONParameter(genesisTxn))

        println("Expected: $expectedValue")
        println("Actual: $actualValue")

        assertEquals(expectedValue, actualValue)

    }
}
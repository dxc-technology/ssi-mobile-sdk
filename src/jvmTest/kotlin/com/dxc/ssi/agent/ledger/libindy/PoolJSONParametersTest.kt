package com.dxc.ssi.agent.ledger.libindy

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.pool.PoolJSONParameters
import org.junit.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString

class PoolJSONParametersTest {
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    fun testCreatePoolLedgerConfigJSONParameter() {

        val genesisTxn = "/Users/ifedyanin/tmp/genesys_file_name.txn"

        val expectedValue = PoolJSONParameters.CreatePoolLedgerConfigJSONParameter(genesisTxn).toJson()
        val actualValue = Json {  }.encodeToString (com.dxc.ssi.agent.ledger.indy.libindy.PoolJSONParameters.CreatePoolLedgerConfigJSONParameter(genesisTxn))

        logger.log(Severity.Debug,"",null) { "Expected: $expectedValue" }
        logger.log(Severity.Debug,"",null) { "Actual: $actualValue" }

        assertEquals(expectedValue, actualValue)

    }
}
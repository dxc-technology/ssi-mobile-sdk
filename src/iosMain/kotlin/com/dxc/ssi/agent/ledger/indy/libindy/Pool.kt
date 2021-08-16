package com.dxc.ssi.agent.ledger.indy.libindy

import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.IntCallback
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.indylib.indy_create_pool_ledger_config
import com.indylib.indy_open_pool_ledger
import com.indylib.indy_set_protocol_version

actual class Pool actual constructor(private val poolHandle: Int) {
    actual companion object {
        private val logger: Kermit = Kermit(LogcatLogger())
        actual suspend fun setProtocolVersion(protocolVersion: Long) {

            val commandHandle = callbackHandler.prepareCallback()

            indy_set_protocol_version(commandHandle, protocolVersion.toULong(), SimpleCallback.callback)

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        actual suspend fun createPoolLedgerConfig(configName: String, config: String) {

            logger.d { "Pool -> In createPoolLedgerConfig" }

            val commandHandle = callbackHandler.prepareCallback()

            indy_create_pool_ledger_config(commandHandle, configName, config, SimpleCallback.callback)

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        @OptIn(ExperimentalUnsignedTypes::class)
        actual suspend fun openPoolLedger(configName: String, config: String): Pool {
            logger.d { "Pool -> In openPoolLedger: configName = $configName, config = $config" }

            val commandHandle = callbackHandler.prepareCallback()

            //TODO: add proper config here
            indy_open_pool_ledger(commandHandle, configName, config, IntCallback.callback)

            logger.d { "Pool -> Before waiting for callback" }

            val openPoolLedgerConfigCallbackResult =
                callbackHandler.waitForCallbackResult(commandHandle) as IntCallback.Result

            logger.d { "Pool -> Out of  openPoolLedger" }
            return Pool(openPoolLedgerConfigCallbackResult.handle)
        }

    }

    //TODO: replace it with property getter/setter
    actual fun getPoolHandle(): Int {
        return poolHandle
    }
}
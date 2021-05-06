package com.dxc.ssi.agent.ledger.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.indylib.indy_create_pool_ledger_config
import com.indylib.indy_open_pool_ledger
import com.indylib.indy_set_protocol_version
import kotlinx.cinterop.staticCFunction

//TODO: unify this, define those methods as expected ones
actual class Pool(private val poolHandle: Int) {

    data class OpenPoolLedgerConfigCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val poolHandle: Int
    ) : CallbackData

    companion object {
        suspend fun setProtocolVersion(protocolVersion: Long) {

            val commandHandle = callbackHandler.prepareCallback()

            indy_set_protocol_version(commandHandle, protocolVersion.toULong(), SimpleCallback.callback)

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        suspend fun createPoolLedgerConfig(configName: String, config: String) {
            println("Pool -> In createPoolLedgerConfig")
            val commandHandle = callbackHandler.prepareCallback()

            indy_create_pool_ledger_config(commandHandle, configName, config, SimpleCallback.callback)

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        @OptIn(ExperimentalUnsignedTypes::class)
        suspend fun openPoolLedger(configName: String, config: String): Pool {
            println("Pool -> In openPoolLedger: configName = $configName, config = $config")

            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction() { commandHandle: Int, errorCode: UInt, poolHandle: Int
                    ->
                    initRuntimeIfNeeded()
                    println("Executing callback in openPoolLedger")
                    callbackHandler.setCallbackResult(
                        OpenPoolLedgerConfigCallbackResult(commandHandle, errorCode, poolHandle)
                    )
                }

            //TODO: add proper config here
            indy_open_pool_ledger(commandHandle, configName, config, callback)
            println("Pool -> Before waiting for callback")
            val openPoolLedgerConfigCallbackResult =
                callbackHandler.waitForCallbackResult(commandHandle) as OpenPoolLedgerConfigCallbackResult

            println("Pool -> Out of  openPoolLedger")
            return Pool(openPoolLedgerConfigCallbackResult.poolHandle)
        }

    }

    //TODO: replace it with property getter/setter
    fun getPoolHandle(): Int {
        return poolHandle
    }
}
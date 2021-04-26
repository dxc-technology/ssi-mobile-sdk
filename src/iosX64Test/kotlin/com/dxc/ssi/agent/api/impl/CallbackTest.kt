package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.indylib.indy_create_wallet
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test


data class CreateWalletResult(
    override val commandHandle: Int,
    override val errorCode: UInt
) : CallbackData


class CallbackTest {

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    fun callbackTest() {


        val config = WalletConfig("testWallet111114")
        val password = WalletPassword("testWalletPassword")

        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)


        val commandHandle = callbackHandler.prepareCallback()

        val callback = staticCFunction<Int, UInt, Unit> { commandHandle: Int, errorCode: UInt
            ->
            initRuntimeIfNeeded()
            callbackHandler.setCallbackResult(CreateWalletResult(commandHandle, errorCode))
        }


        indy_create_wallet(commandHandle, walletConfigJson, walletPasswordJson, callback)

        runBlocking {
            callbackHandler.waitForCallbackResult(commandHandle)
        }

        println("Test finished")
    }
}
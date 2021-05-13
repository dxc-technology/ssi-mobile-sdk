package com.dxc.ssi.agent.api.impl


import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.indylib.indy_create_wallet
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.Ignore

class CallbackTest {

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    @Ignore
    fun callbackTest() {


        val config = WalletConfig("testWallet111114")
        val password = WalletPassword("testWalletPassword")

        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)


        val commandHandle = callbackHandler.prepareCallback()


        indy_create_wallet(commandHandle, walletConfigJson, walletPasswordJson, SimpleCallback.callback)

        runBlocking {
            callbackHandler.waitForCallbackResult(commandHandle)
        }

        println("Test finished")
    }
}
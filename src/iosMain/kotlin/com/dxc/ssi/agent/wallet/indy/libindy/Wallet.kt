package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.IntCallback
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.indylib.indy_close_wallet
import com.indylib.indy_create_wallet
import com.indylib.indy_open_wallet


actual class Wallet actual constructor(private var walletHandle: Int) {

    actual companion object {
        private val logger: Kermit = Kermit(LogcatLogger())

        actual suspend fun createWallet(config: String, credentials: String) {
            val commandHandle = callbackHandler.prepareCallback()

            indy_create_wallet(
                commandHandle,
                config,
                credentials,
                SimpleCallback.callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        actual suspend fun openWallet(config: String, credentials: String): Wallet {
            val commandHandle = callbackHandler.prepareCallback()

            indy_open_wallet(
                commandHandle,
                config,
                credentials,
                IntCallback.callback
            )
            logger.log(Severity.Debug,"",null) { "\"Before waiting for wallet opening callback result: config -> $config, credentials -> $credentials\"" }

            val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as IntCallback.Result
            logger.log(Severity.Debug,"",null) { "After waiting for wallet opening callback result" }
            return Wallet(callbackResult.handle)
        }
    }

    //TODO: replace it with property getter/setter
    actual fun getWalletHandle(): Int {
        return walletHandle
    }

    actual suspend fun closeWallet() {
        val commandHandle = callbackHandler.prepareCallback()
        indy_close_wallet(commandHandle, walletHandle, SimpleCallback.callback)
        callbackHandler.waitForCallbackResult(commandHandle)
    }
}
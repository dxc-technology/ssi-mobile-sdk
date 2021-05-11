package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.IntCallback
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.indylib.indy_create_wallet
import com.indylib.indy_open_wallet
import kotlinx.cinterop.staticCFunction

actual class Wallet(private var walletHandle: Int) {

    companion object {
        suspend fun createWallet(config:String, credentials:String) {
            val commandHandle = callbackHandler.prepareCallback()

            indy_create_wallet(
                commandHandle,
                config,
                credentials,
                SimpleCallback.callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        suspend fun openWallet(config:String, credentials:String) : Wallet {
            val commandHandle = callbackHandler.prepareCallback()

            indy_open_wallet(
                commandHandle,
                config,
                credentials,
                IntCallback.callback
            )

            val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as IntCallback.Result
            return Wallet(callbackResult.handle)
        }
    }

    //TODO: replace it with property getter/setter
    fun getWalletHandle(): Int {
        return walletHandle
    }
}
package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import com.indylib.indy_create_wallet
import com.indylib.indy_open_wallet
import kotlinx.cinterop.staticCFunction

actual class Wallet(private var walletHandle: Int) {

    data class CreateWalletResult(
        override val commandHandle: Int,
        override val errorCode: UInt
    ) : CallbackData

    data class OpenWalletResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val walletHandle: Int
    ) : CallbackData

    companion object {
        fun createWallet(config:String, credentials:String) {
            val commandHandle = callbackHandler.prepareCallback()
            val callback = staticCFunction { commandHandle: Int, errorCode: UInt
                ->
                initRuntimeIfNeeded()
                callbackHandler.setCallbackResult(CreateWalletResult(commandHandle, errorCode))
            }

            indy_create_wallet(
                commandHandle,
                config,
                credentials,
                callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)

        }

        fun openWallet(config:String, credentials:String) : Wallet {
            val commandHandle = callbackHandler.prepareCallback()

            val callback = staticCFunction { commandHandle: Int, errorCode: UInt, walletHandle: Int
                ->
                initRuntimeIfNeeded()
                callbackHandler.setCallbackResult(OpenWalletResult(commandHandle, errorCode, walletHandle))
            }

            indy_open_wallet(
                commandHandle,
                config,
                credentials,
                callback
            )

            val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as OpenWalletResult
            return Wallet(callbackResult.walletHandle)
        }
    }

    fun getWalletHandle(): Int {
        return walletHandle
    }
}
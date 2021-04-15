package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.indylib.indy_create_wallet
import com.indylib.indy_open_wallet
import kotlinx.cinterop.staticCFunction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


actual object WalletHelper {

    data class OpenWalletResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val walletHandle: Int
    ) : CallbackData

    data class CreateWalletResult(
        override val commandHandle: Int,
        override val errorCode: UInt
    ) : CallbackData

    actual fun createOrTrunc(walletName: String, walletPassword: String) {
//TODO: implement this
    }

    private fun openWallet(walletName: String, walletPassword: String): Wallet {
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        val commandHandle = callbackHandler.prepareCallback()

        val callback = staticCFunction { commandHandle: Int, errorCode: UInt, walletHandle: Int
            ->
            initRuntimeIfNeeded()
            callbackHandler.setCallbackResult(OpenWalletResult(commandHandle, errorCode, walletHandle))
        }

        indy_open_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            callback
        )

        val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as OpenWalletResult
        return Wallet(callbackResult.walletHandle)

    }

    actual fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        val commandHandle = callbackHandler.prepareCallback()
        val callback = staticCFunction { commandHandle: Int, errorCode: UInt
            ->
            initRuntimeIfNeeded()
            callbackHandler.setCallbackResult(CreateWalletResult(commandHandle, errorCode))
        }

        indy_create_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            callback
        )

        callbackHandler.waitForCallbackResult(commandHandle)

        return openWallet(walletName, walletPassword)
    }
}
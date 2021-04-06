package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Api
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.indylib.indy_create_wallet
import com.indylib.indy_error_t
import com.indylib.indy_handle_t
import com.indylib.indy_open_wallet
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import platform.posix.sleep

typealias MyCallbackWallet = CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>?
typealias MyCallbackWalletOpen = CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>>

actual object WalletHelper {

    fun exists(walletName: String): Boolean {
        //val walletDir = EnvironmentUtils.getIndyWalletPath(walletName)
        return true // File(walletDir).exists()
    }

    fun openExisting(config: WalletConfig, password: WalletPassword): Wallet {
//        if (!exists(config.id))
//            throw FileNotFoundException("Wallet ${EnvironmentUtils.getIndyWalletPath(config.id)} doesn't exist")
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        return openWallet(walletConfigJson, walletPasswordJson)
    }

    actual fun createOrTrunc(walletName: String, walletPassword: String) {
        //if (exists(config.id))
        //    File(EnvironmentUtils.getIndyWalletPath(config.id)).deleteRecursively()
        //var result = openWallet(walletName, walletPassword)
    }

    fun openWallet(walletName: String, walletPassword: String): Wallet {
        return Wallet()
        val commandHandle = Api.atomicInteger.value++
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        val myExit_cb: MyCallbackWalletOpen = staticCFunction(fun(
            xcommand_handle,
            err,
            callback
        ) {
            initRuntimeIfNeeded()
            return
        })
        val result: indy_error_t = indy_open_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            myExit_cb
        )

    }

    actual fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {
        return Wallet()
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val commandHandle = Api.atomicInteger.value++
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        val myExit_cb: MyCallbackWallet = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
        ) {
            initRuntimeIfNeeded()
            return
        })

        val result = indy_create_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            myExit_cb
        )
        sleep(8)

    }
}
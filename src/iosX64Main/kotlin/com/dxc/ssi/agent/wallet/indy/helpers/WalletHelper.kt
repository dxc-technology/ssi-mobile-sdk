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
import kotlin.native.concurrent.AtomicInt

@SharedImmutable
val rw = ReadWrite()

class ReadWrite {
    private var atomic: AtomicInt = AtomicInt(0)
    fun read(): Int {
        return atomic.value
    }

    fun save(value: Int) {
        atomic.value = value
    }
}

actual object WalletHelper {

    actual fun createOrTrunc(walletName: String, walletPassword: String) {

    }

    private fun openWallet(walletName: String, walletPassword: String): Wallet {
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        val commandHandle = Api.atomicInteger.value++
        val myExitCallbackOpen: CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>> =
            staticCFunction(fun(
                _: indy_handle_t,
                _: indy_error_t,
                indy_handle: indy_handle_t
            ) {
                initRuntimeIfNeeded()
                rw.save(indy_handle)
                return
            })
        indy_open_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            myExitCallbackOpen
        )
        sleep(6)
        return Wallet(rw.read())
    }

    actual fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {
        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        val commandHandle = Api.atomicInteger.value++
        val myExitCallBackCreate: CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>? = staticCFunction(fun(
            _: indy_handle_t,
            _: indy_error_t,
        ) {
            return
        })
        indy_create_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            myExitCallBackCreate
        )
        sleep(6)
        return openWallet(walletName, walletPassword)
    }
}
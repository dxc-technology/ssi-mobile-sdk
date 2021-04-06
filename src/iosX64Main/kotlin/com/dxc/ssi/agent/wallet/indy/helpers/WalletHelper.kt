package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Api
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.indylib.indy_create_wallet
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual object WalletHelper {

    fun exists(walletName: String): Boolean {
        //val walletDir = EnvironmentUtils.getIndyWalletPath(walletName)
        return true // File(walletDir).exists()
    }

    actual fun createOrTrunc(walletName: String, walletPassword: String) {
        //if (exists(config.id))
        //    File(EnvironmentUtils.getIndyWalletPath(config.id)).deleteRecursively()
        openOrCreate(walletName, walletPassword)
    }

    actual fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {

        val config = WalletConfig(walletName)
        val password = WalletPassword(walletPassword)
        val commandHandle = Api.atomicInteger.value++
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)
        val oidCb = null
        val result = indy_create_wallet(
            commandHandle,
            walletConfigJson,
            walletPasswordJson,
            oidCb
        )
        return Wallet()
    }
}
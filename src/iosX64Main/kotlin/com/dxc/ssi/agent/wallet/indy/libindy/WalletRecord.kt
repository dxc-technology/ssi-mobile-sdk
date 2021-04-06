package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.wallet.indy.libindy.Api.Companion.atomicInteger
import com.indylib.indy_add_wallet_record
import com.indylib.indy_error_t
import com.indylib.indy_get_wallet_record
import com.indylib.indy_update_wallet_record_value

actual class WalletRecord {
    actual companion object {
        actual fun get(
            wallet: Wallet,
            type: String,
            id: String,
            optionsJson: String
        ): String {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
            val stringCb = null
            val result: indy_error_t = indy_get_wallet_record(
                commandHandle,
                walletHandle,
                type,
                id,
                optionsJson,
                stringCb
            )
            if (result.toInt() != 0)
                throw WalletItemNotFoundException()
            else
                return ""
        }

        actual fun add(
            wallet: Wallet,
            type: String,
            id: String,
            value: String,
            tagsJson: String?
        ) {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
            val voidCb = null
            val result = indy_add_wallet_record(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                tagsJson,
                voidCb
            )
            if (result.toInt() != 0)
                throw WalletItemNotFoundException()
            else
                return
        }

        actual fun updateValue(
            wallet: Wallet,
            type: String,
            id: String,
            value: String
        ) {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
            val voidCb = null
            val result = indy_update_wallet_record_value(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                voidCb
            )
            if (result.toInt() != 0)
                throw WalletItemNotFoundException()
            else
                return
        }
    }
}
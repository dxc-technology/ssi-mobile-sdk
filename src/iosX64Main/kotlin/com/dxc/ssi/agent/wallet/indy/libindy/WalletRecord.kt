package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.indy_add_wallet_record
import com.indylib.indy_get_wallet_record
import com.indylib.indy_update_wallet_record_value
import kotlin.native.concurrent.AtomicInt

@ThreadLocal
private var atomicInteger: AtomicInt = AtomicInt(1)

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
            val result = indy_get_wallet_record(
                    commandHandle,
            walletHandle,
            type,
            id,
            optionsJson,
            stringCb)
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
        }
    }
}
package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.non_secrets.WalletRecord

actual class WalletRecord {
    actual companion object {
        actual fun get(wallet: Wallet, type: String, id: String, optionsJson: String): String {
            return WalletRecord.get(wallet, type, id, optionsJson).get()
        }

        actual fun add(wallet: Wallet, type: String, id: String, value: String, tagsJson: String?) {
            WalletRecord.add(wallet, type, id, value, tagsJson).get()
        }

        actual fun updateValue(wallet: Wallet, type: String, id: String, value: String) {
            WalletRecord.updateValue(wallet, type, id, value)
        }
    }

}
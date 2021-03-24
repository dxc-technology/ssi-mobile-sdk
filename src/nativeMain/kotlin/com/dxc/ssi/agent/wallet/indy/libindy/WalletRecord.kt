package com.dxc.ssi.agent.wallet.indy.libindy

actual class WalletRecord {
    actual companion object {
        actual fun get(
            wallet: Wallet,
            type: String,
            id: String,
            optionsJson: String
        ): String {
            TODO("Not yet implemented")
        }

        actual fun add(
            wallet: Wallet,
            type: String,
            id: String,
            value: String,
            tagsJson: String?
        ) {
        }

        actual fun updateValue(
            wallet: Wallet,
            type: String,
            id: String,
            value: String
        ) {
        }

        actual fun updateTags(
            wallet: Wallet,
            type: String,
            id: String,
            tagsJson: String
        ) {
        }
    }

}
package com.dxc.ssi.agent.wallet.indy.libindy

actual class WalletRecord {
    actual companion object {
        actual suspend fun get(
            wallet: Wallet,
            type: String,
            id: String,
            optionsJson: String
        ): String {
            TODO("Not yet implemented")
        }

        actual suspend fun add(
            wallet: Wallet,
            type: String,
            id: String,
            value: String,
            tagsJson: String?
        ) {
        }

        actual suspend fun updateValue(
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

        actual fun delete(
            wallet: Wallet,
            type: String,
            id: String
        ) {
        }
    }

}
package com.dxc.ssi.agent.wallet.indy.libindy

expect class WalletRecord {
    companion object {
        suspend fun get(wallet: Wallet, type: String, id: String, optionsJson: String): String
        suspend fun add(wallet: Wallet, type: String, id: String, value: String, tagsJson: String?)
        suspend fun updateValue(wallet: Wallet, type: String, id: String, value: String)
        suspend fun updateTags(wallet: Wallet, type: String, id: String, tagsJson: String)
        suspend fun delete(wallet: Wallet, type: String, id: String)
    }
}
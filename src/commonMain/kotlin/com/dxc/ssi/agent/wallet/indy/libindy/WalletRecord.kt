package com.dxc.ssi.agent.wallet.indy.libindy

expect class WalletRecord {
    companion object {
        fun get(wallet: Wallet, type: String, id: String, optionsJson: String): String
        fun add(wallet: Wallet, type: String, id: String, value: String, tagsJson: String?)
        fun updateValue(wallet: Wallet, type: String, id: String, value: String)
        fun updateTags(wallet: Wallet, type: String, id: String, tagsJson: String)
        fun delete(wallet: Wallet, type: String, id: String)
    }
}
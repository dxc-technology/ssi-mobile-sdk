package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.non_secrets.WalletSearch

actual class WalletSearch actual constructor() {

    private lateinit var walletSearch: org.hyperledger.indy.sdk.non_secrets.WalletSearch

    actual fun open(wallet: Wallet, type: String, queryJson: String, optionsJson: String) {
        walletSearch =
            WalletSearch.open(wallet, type, queryJson, optionsJson).get()
    }

    actual fun closeSearch() {
        WalletSearch.closeSearch(walletSearch).get()
    }

    actual fun searchFetchNextRecords(wallet: Wallet, count: Int): String {
        return WalletSearch.searchFetchNextRecords(wallet, walletSearch, count).get()
    }
}
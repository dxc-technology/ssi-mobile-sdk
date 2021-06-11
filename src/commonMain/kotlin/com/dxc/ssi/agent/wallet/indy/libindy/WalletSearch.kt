package com.dxc.ssi.agent.wallet.indy.libindy

expect class WalletSearch() {

    //TODO: see how exceptions can be added into signature of those expected functions and all others
    suspend fun open(wallet: Wallet, type: String, queryJson: String, optionsJson: String)
    suspend fun closeSearch()
    suspend fun searchFetchNextRecords(wallet: Wallet, count: Int): String
}
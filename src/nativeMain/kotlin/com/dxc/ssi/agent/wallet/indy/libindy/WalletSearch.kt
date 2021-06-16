package com.dxc.ssi.agent.wallet.indy.libindy

actual class WalletSearch actual constructor() {
    actual suspend fun open(
        wallet: Wallet,
        type: String,
        queryJson: String,
        optionsJson: String
    ) {
        TODO("Not yet implemented")
    }

    actual suspend fun closeSearch() {
        TODO("Not yet implemented")
    }

    actual suspend fun searchFetchNextRecords(
        wallet: Wallet,
        count: Int
    ): String {
        TODO("Not yet implemented")
    }

}
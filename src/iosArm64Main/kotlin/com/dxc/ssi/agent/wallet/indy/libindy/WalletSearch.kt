package com.dxc.ssi.agent.wallet.indy.libindy

actual class WalletSearch actual constructor() {
    actual fun open(
        wallet: Wallet,
        type: String,
        queryJson: String,
        optionsJson: String
    ) {
    }

    actual fun closeSearch() {
    }

    actual fun searchFetchNextRecords(
        wallet: Wallet,
        count: Int
    ): String {
        TODO("Not yet implemented")
    }

}
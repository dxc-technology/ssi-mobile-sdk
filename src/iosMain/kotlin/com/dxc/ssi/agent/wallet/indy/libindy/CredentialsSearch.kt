package com.dxc.ssi.agent.wallet.indy.libindy

actual class CredentialsSearch actual constructor() {
    actual suspend fun open(wallet: Wallet, queryJson: String?) {
    }

    actual suspend fun fetchNextCredentials(count: Int): String {
        TODO("Not yet implemented")
    }

    actual suspend fun closeSearch() {
    }

}
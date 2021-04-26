package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Wallet

actual object WalletHelper {
    actual suspend fun createOrTrunc(walletName: String, walletPassword: String) {
    }

    actual suspend fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {
        TODO("Not yet implemented")
    }
}
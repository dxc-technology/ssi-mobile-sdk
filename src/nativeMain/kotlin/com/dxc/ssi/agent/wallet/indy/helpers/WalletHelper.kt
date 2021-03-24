package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Wallet

actual object WalletHelper {
    actual fun createOrTrunc(walletName: String, walletPassword: String) {
    }

    actual fun openOrCreate(
        walletName: String,
        walletPassword: String
    ): Wallet {
        TODO("Not yet implemented")
    }
}
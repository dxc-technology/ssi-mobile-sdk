package com.dxc.ssi.agent.wallet.indy.libindy

actual class Wallet {

    private var walletHandle = 0

    private fun Wallet(walletHandle: Int) {
        this.walletHandle = walletHandle
    }

    fun getWalletHandle(): Int {
        return walletHandle
    }
}
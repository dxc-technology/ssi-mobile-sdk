package com.dxc.ssi.agent.wallet.indy.libindy

actual class Wallet(private var walletHandle: Int) {

    fun getWalletHandle(): Int {
        return walletHandle
    }
}
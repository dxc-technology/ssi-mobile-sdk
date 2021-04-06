package com.dxc.ssi.agent.wallet.indy.libindy

actual class Wallet {

    private var walletHandle = 0

    private fun Wallet(walletHandle: Int) {
        this.walletHandle = walletHandle
    }

    /**
     * Gets the handle for the wallet.
     *
     * @return The handle for the wallet.
     */
    fun getWalletHandle(): Int {
        return walletHandle
    }

}
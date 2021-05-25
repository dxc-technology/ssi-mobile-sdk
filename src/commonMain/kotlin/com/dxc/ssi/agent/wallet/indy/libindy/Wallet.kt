package com.dxc.ssi.agent.wallet.indy.libindy

expect class Wallet(walletHandle:Int) {

    //TODO: check if it can be replaced with property access syntax
    fun getWalletHandle(): Int

    companion object {
        suspend fun createWallet(config: String, credentials: String)
        suspend fun openWallet(config: String, credentials: String): Wallet
    }

}
package com.dxc.ssi.agent.wallet.indy.libindy

actual class Wallet actual constructor(walletHandle: Int) {
    actual fun getWalletHandle(): Int {
        TODO("Not yet implemented")
    }

    actual suspend fun closeWallet() {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual suspend fun createWallet(config: String, credentials: String) {
            TODO("Not yet implemented")
        }

        actual suspend fun openWallet(
            config: String,
            credentials: String
        ): Wallet {
            TODO("Not yet implemented")
        }
    }

}
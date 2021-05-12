package com.dxc.ssi.agent.wallet.indy.libindy

actual class Wallet actual constructor(
    private val walletHandle: Int
) {

    constructor(walletHandle: Int, wallet:org.hyperledger.indy.sdk.wallet.Wallet) : this(walletHandle) {
        this.wallet = wallet
    }

    //Storing original wallet as it is taken by java wrapper
    lateinit var wallet: org.hyperledger.indy.sdk.wallet.Wallet

    actual fun getWalletHandle(): Int {
        return walletHandle
    }


    actual companion object {
        actual suspend fun createWallet(config: String, credentials: String) {
            org.hyperledger.indy.sdk.wallet.Wallet.createWallet(config, credentials).get()
        }

        actual suspend fun openWallet(
            config: String,
            credentials: String
        ): Wallet {
            val result = org.hyperledger.indy.sdk.wallet.Wallet.openWallet(config, credentials).get()
            return Wallet(result.walletHandle, result)
        }
    }
}
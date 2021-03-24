package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Wallet

expect object WalletHelper {
    fun createOrTrunc(walletName: String, walletPassword: String)
    fun openOrCreate(walletName: String, walletPassword: String): Wallet
}
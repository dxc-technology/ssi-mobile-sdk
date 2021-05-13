package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Wallet

expect object WalletHelper {
    suspend fun createOrTrunc(walletName: String, walletPassword: String)
    suspend fun openOrCreate(walletName: String, walletPassword: String): Wallet
}
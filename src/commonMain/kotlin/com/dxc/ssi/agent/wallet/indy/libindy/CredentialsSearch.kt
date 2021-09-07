package com.dxc.ssi.agent.wallet.indy.libindy

expect class CredentialsSearch() {

    suspend fun open(wallet:Wallet,queryJson: String?)
    suspend fun fetchNextCredentials(count: Int): String
    suspend fun closeSearch()
}
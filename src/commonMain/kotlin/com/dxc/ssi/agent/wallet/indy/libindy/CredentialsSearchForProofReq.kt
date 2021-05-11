package com.dxc.ssi.agent.wallet.indy.libindy

expect class CredentialsSearchForProofReq() {

    suspend fun open(wallet:Wallet,proofReqJson: String, extraQueryJson: String?)
    suspend fun fetchNextCredentials(itemRef: String, count: Int): String
    suspend fun closeSearch()
}
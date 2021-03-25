package com.dxc.ssi.agent.wallet.indy.libindy

expect class CredentialsSearchForProofReq() {

    fun open(wallet:Wallet,proofReqJson: String, extraQueryJson: String?)
    fun fetchNextCredentials(itemRef: String, count: Int): String
    fun closeSearch()
}
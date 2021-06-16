package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.anoncreds.CredentialsSearchForProofReq

actual class CredentialsSearchForProofReq actual constructor() {

    private lateinit var credentialsSearchForProofReq: CredentialsSearchForProofReq

    actual suspend fun open(wallet: Wallet, proofReqJson: String, extraQueryJson: String?) {

        println("CredentialsSearchForProofReq.open with" +
                "wallet = ${wallet.wallet}" +
                "proofReqJson = $proofReqJson" +
                "extraQueryJson = $extraQueryJson")

        credentialsSearchForProofReq = CredentialsSearchForProofReq.open(wallet.wallet, proofReqJson, extraQueryJson).get()
    }

    actual suspend fun fetchNextCredentials(itemRef: String, count: Int): String {
        return credentialsSearchForProofReq.fetchNextCredentials(itemRef, count).get()
    }

    actual suspend fun closeSearch() {
        credentialsSearchForProofReq.closeSearch().get()
    }

}
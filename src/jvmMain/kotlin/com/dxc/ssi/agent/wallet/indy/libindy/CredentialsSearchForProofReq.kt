package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.anoncreds.CredentialsSearchForProofReq

actual class CredentialsSearchForProofReq actual constructor() {

    private lateinit var credentialsSearchForProofReq: CredentialsSearchForProofReq

    actual fun open(wallet: Wallet, proofReqJson: String, extraQueryJson: String?) {
        credentialsSearchForProofReq = CredentialsSearchForProofReq.open(wallet, proofReqJson, extraQueryJson).get()
    }

    actual fun fetchNextCredentials(itemRef: String, count: Int): String {
        return credentialsSearchForProofReq.fetchNextCredentials(itemRef, count).get()
    }

    actual fun closeSearch() {
        credentialsSearchForProofReq.closeSearch().get()
    }

}
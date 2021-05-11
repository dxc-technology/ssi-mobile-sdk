package com.dxc.ssi.agent.wallet.indy.libindy

actual class CredentialsSearchForProofReq actual constructor() {
    actual suspend fun open(
        wallet: Wallet,
        proofReqJson: String,
        extraQueryJson: String?
    ) {
        TODO("Not yet implemented")
    }

    actual suspend fun fetchNextCredentials(itemRef: String, count: Int): String {
        TODO("Not yet implemented")
    }

    actual suspend fun closeSearch() {
        TODO("Not yet implemented")

    }


}
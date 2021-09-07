package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.anoncreds.CredentialsSearch

actual class CredentialsSearch actual constructor() {

    private lateinit var credentialsSearch: CredentialsSearch

    actual suspend fun open(wallet: Wallet, queryJson: String?) {
        credentialsSearch = CredentialsSearch.open(wallet.wallet, queryJson).get()
    }

    actual suspend fun fetchNextCredentials(count: Int): String {
        return credentialsSearch.fetchNextCredentials(count).get()
    }

    actual suspend fun closeSearch() {
        credentialsSearch.closeSearch().get()
    }

}
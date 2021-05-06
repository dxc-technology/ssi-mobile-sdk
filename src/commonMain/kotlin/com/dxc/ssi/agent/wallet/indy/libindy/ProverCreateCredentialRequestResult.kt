package com.dxc.ssi.agent.wallet.indy.libindy

class ProverCreateCredentialRequestResult(
    private val credentialRequestJson: String,
    private val credentialRequestMetadataJson: String
) {

    fun getCredentialRequestJson(): String {
        return credentialRequestJson
    }

    fun getCredentialRequestMetadataJson(): String {
        return credentialRequestMetadataJson
    }

}
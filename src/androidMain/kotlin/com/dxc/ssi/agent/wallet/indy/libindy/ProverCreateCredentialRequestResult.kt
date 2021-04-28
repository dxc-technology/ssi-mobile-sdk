package com.dxc.ssi.agent.wallet.indy.libindy

actual class ProverCreateCredentialRequestResult(
    private val credentialRequestJson: String,
    private val credentialRequestMetadataJson: String
) {

    actual fun getCredentialRequestJson(): String {
        return credentialRequestJson
    }

    actual fun getCredentialRequestMetadataJson(): String {
        return credentialRequestMetadataJson
    }

}
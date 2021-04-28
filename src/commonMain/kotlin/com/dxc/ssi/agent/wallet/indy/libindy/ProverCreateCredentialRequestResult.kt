package com.dxc.ssi.agent.wallet.indy.libindy

expect class ProverCreateCredentialRequestResult {

    fun getCredentialRequestJson(): String
    fun getCredentialRequestMetadataJson(): String
}
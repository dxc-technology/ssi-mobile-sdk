package com.dxc.ssi.agent.wallet.indy.libindy

expect class Anoncreds {
    companion object {
        suspend fun proverCreateCredentialReq(
            wallet: Wallet,
            proverDid: String,
            credentialOfferJson: String,
            credentialDefJson: String,
            masterSecretId: String
        ): ProverCreateCredentialRequestResult

        suspend fun proverCreateMasterSecret(wallet: Wallet, masterSecretId: String): String


        suspend fun proverStoreCredential(
            wallet: Wallet,
            credId: String?,
            credReqMetadataJson: String,
            credJson: String,
            credDefJson: String,
            revRegDefJson: String?
        ): String

        fun createRevocationState(
            blobStorageReaderHandle: Int,
            revRegDef: String,
            revRegDelta: String,
            timestamp: Long,
            credRevId: String
        ): String

        fun proverCreateProof(
            wallet: Wallet,
            proofRequest: String,
            requestedCredentials: String,
            masterSecret: String?,
            schemas: String,
            credentialDefs: String,
            revStates: String
        ): String
    }
}
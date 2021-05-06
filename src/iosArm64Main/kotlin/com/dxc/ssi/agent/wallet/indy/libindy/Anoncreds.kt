package com.dxc.ssi.agent.wallet.indy.libindy

actual class Anoncreds {
    actual companion object {
        actual fun proverCreateCredentialReq(
            wallet: Wallet,
            proverDid: String,
            credentialOfferJson: String,
            credentialDefJson: String,
            masterSecretId: String
        ): ProverCreateCredentialRequestResult {
            TODO("Not yet implemented")
        }

        actual suspend fun proverCreateMasterSecret(
            wallet: Wallet,
            masterSecretId: String
        ): String {
            TODO("Not yet implemented")
        }

        actual  suspend fun proverStoreCredential(
            wallet: Wallet,
            credId: String?,
            credReqMetadataJson: String,
            credJson: String,
            credDefJson: String,
            revRegDefJson: String?
        ): String {
            TODO("Not yet implemented")
        }

        actual fun createRevocationState(
            blobStorageReaderHandle: Int,
            revRegDef: String,
            revRegDelta: String,
            timestamp: Long,
            credRevId: String
        ): String {
            TODO("Not yet implemented")
        }

        actual fun proverCreateProof(
            wallet: Wallet,
            proofRequest: String,
            requestedCredentials: String,
            masterSecret: String?,
            schemas: String,
            credentialDefs: String,
            revStates: String
        ): String {
            TODO("Not yet implemented")
        }


    }
}
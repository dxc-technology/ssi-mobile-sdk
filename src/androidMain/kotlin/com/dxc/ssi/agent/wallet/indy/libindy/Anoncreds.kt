package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.exceptions.indy.IndyJvmToCommonExceptionConverter
import org.hyperledger.indy.sdk.anoncreds.Anoncreds

actual class Anoncreds {
    actual companion object {
        actual suspend fun proverCreateCredentialReq(
            wallet: Wallet,
            proverDid: String,
            credentialOfferJson: String,
            credentialDefJson: String,
            masterSecretId: String
        ): ProverCreateCredentialRequestResult {

            val proverCreateCredentialReqResultIndy = Anoncreds.proverCreateCredentialReq(
                wallet.wallet,
                proverDid,
                credentialOfferJson,
                credentialDefJson,
                masterSecretId
            ).get()

            return ProverCreateCredentialRequestResult(
                credentialRequestJson = proverCreateCredentialReqResultIndy.credentialRequestJson,
                credentialRequestMetadataJson = proverCreateCredentialReqResultIndy.credentialRequestMetadataJson
            )

        }

        actual suspend fun proverCreateMasterSecret(
            wallet: Wallet,
            masterSecretId: String
        ): String {
            val converter = IndyJvmToCommonExceptionConverter<String>()
            return converter.convertException {
                Anoncreds.proverCreateMasterSecret(wallet.wallet, masterSecretId).get()
            }
        }

        actual suspend fun proverStoreCredential(
            wallet: Wallet,
            credId: String?,
            credReqMetadataJson: String,
            credJson: String,
            credDefJson: String,
            revRegDefJson: String?
        ): String {
            return Anoncreds.proverStoreCredential(
                wallet.wallet,
                credId,
                credReqMetadataJson,
                credJson,
                credDefJson,
                revRegDefJson
            ).get()
        }

        actual suspend fun proverGetCredential(
            wallet: Wallet,
            credId: String
        ): String {
            return Anoncreds.proverGetCredential(wallet.wallet, credId).get()
        }

        actual suspend fun createRevocationState(
            blobStorageReaderHandle: Int,
            revRegDef: String,
            revRegDelta: String,
            timestamp: Long,
            credRevId: String
        ): String {
            return Anoncreds.createRevocationState(
                blobStorageReaderHandle,
                revRegDef,
                revRegDelta,
                timestamp,
                credRevId
            ).get()
        }

        actual suspend fun proverCreateProof(
            wallet: Wallet,
            proofRequest: String,
            requestedCredentials: String,
            masterSecret: String?,
            schemas: String,
            credentialDefs: String,
            revStates: String
        ): String {
            return Anoncreds.proverCreateProof(
                wallet.wallet,
                proofRequest,
                requestedCredentials,
                masterSecret,
                schemas,
                credentialDefs,
                revStates
            ).get()
        }


    }
}
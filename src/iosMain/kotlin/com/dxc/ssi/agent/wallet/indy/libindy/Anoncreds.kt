package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.*
import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString

actual class Anoncreds {

    data class ProverCreateCredentialReqCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val credReqJson: String,
        val credReqMetadataJson: String
    ) : CallbackData

    actual companion object {
        actual suspend fun proverCreateCredentialReq(
            wallet: Wallet,
            proverDid: String,
            credentialOfferJson: String,
            credentialDefJson: String,
            masterSecretId: String
        ): ProverCreateCredentialRequestResult {

            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt, credReqJson: CPointer<ByteVarOf<Byte>>?, credReqMetadataJson: CPointer<ByteVarOf<Byte>>?
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        ProverCreateCredentialReqCallbackResult(
                            commandHandle,
                            errorCode,
                            credReqJson!!.toKString(),
                            credReqMetadataJson!!.toKString()
                        )
                    )
                }

            indy_prover_create_credential_req(
                commandHandle,
                wallet.getWalletHandle(),
                proverDid,
                credentialOfferJson,
                credentialDefJson,
                masterSecretId,
                callback
            )

            val result = callbackHandler.waitForCallbackResult(commandHandle) as ProverCreateCredentialReqCallbackResult
            return ProverCreateCredentialRequestResult(result.credReqJson, result.credReqMetadataJson)


        }

        actual suspend fun proverCreateMasterSecret(
            wallet: Wallet,
            masterSecretId: String
        ): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_prover_create_master_secret(
                commandHandle,
                wallet.getWalletHandle(),
                masterSecretId,
                StringCallback.callback
            )

            val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result

            return callbackResult.stringResult

        }

        actual suspend fun proverStoreCredential(
            wallet: Wallet,
            credId: String?,
            credReqMetadataJson: String,
            credJson: String,
            credDefJson: String,
            revRegDefJson: String?
        ): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_prover_store_credential(
                commandHandle,
                wallet.getWalletHandle(),
                credId,
                credReqMetadataJson,
                credJson,
                credDefJson,
                revRegDefJson,
                StringCallback.callback
            )

            println("Pool -> Before waiting for callback")

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult

        }

        actual suspend fun proverGetCredential(
            wallet: Wallet,
            credId: String
        ): String {
            TODO("Not yet implemented")
        }

        actual suspend fun createRevocationState(
            blobStorageReaderHandle: Int,
            revRegDef: String,
            revRegDelta: String,
            timestamp: Long,
            credRevId: String
        ): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_create_revocation_state(
                commandHandle,
                blobStorageReaderHandle,
                revRegDef,
                revRegDelta,
                timestamp.toULong(),
                credRevId,
                StringCallback.callback)

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult
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

            val commandHandle = callbackHandler.prepareCallback()

            indy_prover_create_proof(
                commandHandle,
                wallet.getWalletHandle(),
                proofRequest,
                requestedCredentials,
                masterSecret,
                schemas,
                credentialDefs,
                revStates,
                StringCallback.callback)

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult
        }


    }
}
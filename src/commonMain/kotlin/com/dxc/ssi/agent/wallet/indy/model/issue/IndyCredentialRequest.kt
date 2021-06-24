package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequest
import com.dxc.ssi.agent.utils.AnyToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredentialRequest(
    @SerialName("prover_did") val proverDid: String,
    @SerialName("cred_def_id") val credentialDefinitionIdRaw: String,

    @SerialName("blinded_ms")
    @Serializable(with = AnyToStringSerializer::class)
    val blindedMs: String,

    @SerialName("blinded_ms_correctness_proof")
    @Serializable(with = AnyToStringSerializer::class)
    val blindedMsCorrectnessProof: String,

    val nonce: String
) : CredentialRequest

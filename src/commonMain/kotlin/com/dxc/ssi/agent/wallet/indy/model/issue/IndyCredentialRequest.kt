package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequest
import com.dxc.ssi.agent.utils.JsonElementToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredentialRequest(
    @SerialName("prover_did") val proverDid: String,
    @SerialName("cred_def_id") val credentialDefinitionIdRaw: String,

    @SerialName("blinded_ms")
    @Serializable(with = JsonElementToStringSerializer::class)
    val blindedMs: String,

    @SerialName("blinded_ms_correctness_proof")
    @Serializable(with = JsonElementToStringSerializer::class)
    val blindedMsCorrectnessProof: String,

    val nonce: String
) : CredentialRequest

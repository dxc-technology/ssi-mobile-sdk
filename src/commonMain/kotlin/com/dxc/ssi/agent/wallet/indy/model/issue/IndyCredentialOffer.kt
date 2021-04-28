package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialOffer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredentialOffer(
    @SerialName("schema_id") val schemaIdRaw: String,
    @SerialName("cred_def_id") val credentialDefinitionIdRaw: String,
    @SerialName("key_correctness_proof") val keyCorrectnessProof: IndyKeyCorrectnessProof,
    val nonce: String
) : CredentialOffer


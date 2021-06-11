package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.Credential
import com.dxc.ssi.agent.utils.AnyToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredential(
    @SerialName("schema_id")
    val schemaIdRaw: String,

    @SerialName("cred_def_id")
    val credentialDefinitionIdRaw: String,

    @SerialName("rev_reg")
    @Serializable(with = AnyToStringSerializer::class)
    val revocationRegistry: String?,

    @Serializable(with = AnyToStringSerializer::class)
    val witness: String?,

    @SerialName("rev_reg_id")
    val revocationRegistryIdRaw: String?,

    //TODO: if needed use this map
    //val values: Map<String, CredentialValue>,
    @Serializable(with = AnyToStringSerializer::class)
    val values: String,

    //val signature: Map<String, RawJsonMap?>,
    //TODO: if needed use this map
    @Serializable(with = AnyToStringSerializer::class)
    val signature: String,

    @SerialName("signature_correctness_proof")
    @Serializable(with = AnyToStringSerializer::class)
    val signatureCorrectnessProof: String
) : Credential

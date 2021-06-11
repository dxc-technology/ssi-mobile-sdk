package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CredentialReference(
    @SerialName("schema_id") val schemaIdRaw: String,
    @SerialName("cred_def_id") val credentialDefinitionIdRaw: String,
    val referent: String,
    @SerialName("attrs") val attributes: String,
    @SerialName("cred_rev_id") val credentialRevocationId: String?,
    @SerialName("rev_reg_id") val revocationRegistryIdRaw: String?
)
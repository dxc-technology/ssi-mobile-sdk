package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProofIdentifier(
    @SerialName("schema_id")  val schemaIdRaw: String,
    @SerialName("cred_def_id")  val credentialDefinitionIdRaw: String,
    @SerialName("rev_reg_id")  val revocationRegistryIdRaw: String?,
    val timestamp: Long?
) 
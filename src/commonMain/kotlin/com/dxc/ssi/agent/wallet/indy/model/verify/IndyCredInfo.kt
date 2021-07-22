package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.didcomm.model.verify.data.CredentialInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredInfo(
    val referent: String,
    val attrs: Map<String, String>,
    @SerialName("schema_id") val schemaId: String,
    @SerialName("cred_def_id") val credDefId: String,
    @SerialName("rev_reg_id") val revRegId: String?,
    @SerialName("cred_rev_id") val credRevId: String?
) : CredentialInfo

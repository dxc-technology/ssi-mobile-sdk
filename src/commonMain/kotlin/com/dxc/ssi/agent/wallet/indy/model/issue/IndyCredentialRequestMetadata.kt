package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequestMetadata
import com.dxc.ssi.agent.utils.JsonElementToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredentialRequestMetadata(
    @SerialName("master_secret_blinding_data")
    @Serializable(with = JsonElementToStringSerializer::class)
    val masterSecretBlindingData: String,
    @SerialName("master_secret_name")
    val masterSecretName: String,
    val nonce: String
):CredentialRequestMetadata

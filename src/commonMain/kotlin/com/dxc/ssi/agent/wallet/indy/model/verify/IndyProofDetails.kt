package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.utils.JsonElementToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyProofDetails(
    @SerialName("primary_proof")
    @Serializable(JsonElementToStringSerializer::class)
    val primaryProof: String,
    @SerialName("non_revoc_proof") val nonRevokedProof: String?
)
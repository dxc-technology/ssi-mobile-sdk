package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.utils.AnyToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyProofDetails(
    @SerialName("primary_proof")
    @Serializable(AnyToStringSerializer::class)
    val primaryProof: String,
    @SerialName("non_revoc_proof") val nonRevokedProof: String?
)
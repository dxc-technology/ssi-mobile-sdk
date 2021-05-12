package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevealedAttributeReference(
    @SerialName("sub_proof_index") val subProofIndex: Int,
    val raw: String,
    val encoded: String
)
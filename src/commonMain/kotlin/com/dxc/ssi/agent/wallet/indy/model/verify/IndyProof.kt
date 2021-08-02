package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.utils.JsonElementToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyProof(
    val proofs: List<IndyProofDetails>,

    @SerialName("aggregated_proof")
    @Serializable(JsonElementToStringSerializer::class)
    val aggregatedProof: String
)
package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.utils.AnyToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyProof(
    val proofs: List<IndyProofDetails>,

    @SerialName("aggregated_proof")
    @Serializable(AnyToStringSerializer::class)
    val aggregatedProof: String
)
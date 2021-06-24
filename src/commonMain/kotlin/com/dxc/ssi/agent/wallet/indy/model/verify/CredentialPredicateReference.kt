package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CredentialPredicateReference(
    val name: String,
    @SerialName("p_value") val pValue: Int,
    @SerialName("p_type") val pType: String = ">=",
    val restrictions: List<Restriction>
)

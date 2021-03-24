package com.dxc.ssi.agent.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DidConfig(
    val did: String? = null,
    val seed: String? = null,
    @SerialName("crypto_type") val cryptoType: String? = null,
    val cid: Boolean? = null
)

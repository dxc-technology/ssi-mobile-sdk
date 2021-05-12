package com.dxc.ssi.agent.wallet.indy.model.issue

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MasterSecretBlindingData(
    @SerialName("v_prime") val vPrime: String,
    @SerialName("vr_prime") val vrPrime: String?
)

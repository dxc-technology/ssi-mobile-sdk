package com.dxc.ssi.agent.wallet.indy.model.issue

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyKeyCorrectnessProof(
    val c: String,
    @SerialName("xz_cap") val xzCap: String,
    @SerialName("xr_cap") val xrCap: List<List<String>>
)
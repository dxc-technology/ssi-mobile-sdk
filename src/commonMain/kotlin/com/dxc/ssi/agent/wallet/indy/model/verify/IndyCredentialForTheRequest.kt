package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyCredentialForTheRequest(
    @SerialName("cred_info") val credInfo: IndyCredInfo,
    val interval: Interval?
)

package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedPredicateInfo(
    @SerialName("cred_id") val credentialId: String,
    val timestamp: Long? = null
)

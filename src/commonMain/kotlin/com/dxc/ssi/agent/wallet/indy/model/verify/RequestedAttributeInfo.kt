package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedAttributeInfo(
    @SerialName("cred_id") val credentialId: String,
    val revealed: Boolean = true,
    val timestamp: Long? = null
)

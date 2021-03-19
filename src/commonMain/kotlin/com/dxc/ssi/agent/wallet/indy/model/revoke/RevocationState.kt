package com.dxc.ssi.agent.wallet.indy.model.revoke

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
//TODO: add intelligence to this class
data class RevocationState(
    val witness: String,
    @SerialName("rev_reg") val revocationRegistry: String,
    val timestamp: Long,
    @Transient var revocationRegistryIdRaw: String? = null
)
package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restriction(
    @SerialName("schema_name") val schemaName: String?
)

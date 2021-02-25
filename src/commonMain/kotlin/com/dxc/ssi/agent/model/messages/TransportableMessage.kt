package com.dxc.ssi.agent.model.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransportableMessage(
    @SerialName("Payload") val payload: String,
    @SerialName("Packed") val packed: Boolean) {

}

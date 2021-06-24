package com.dxc.ssi.agent.model.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
//TODO: deal with mess with all of those different messages
data class ReceivedUnpackedMessage(
    val message: String,
    @SerialName("recipient_verkey") val recipientVerKey: String,
    @SerialName("sender_verkey") val senderVerKey: String
)
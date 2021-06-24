package com.dxc.ssi.agent.model.messages

import com.dxc.ssi.agent.model.PeerConnection


data class MessageContext(
    val connection: PeerConnection?,
    val receivedUnpackedMessage: ReceivedUnpackedMessage
)
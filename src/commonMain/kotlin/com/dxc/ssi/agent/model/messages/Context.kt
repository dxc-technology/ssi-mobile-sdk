package com.dxc.ssi.agent.model.messages

import com.dxc.ssi.agent.model.PeerConnection


data class Context(
    val connection: PeerConnection? = null,
    val receivedUnpackedMessage: ReceivedUnpackedMessage? = null
)
package com.dxc.ssi.agent.model.messages

import com.dxc.ssi.agent.model.Connection


data class MessageContext(
    val connection: Connection?,
    val receivedUnpackedMessage: ReceivedUnpackedMessage
)
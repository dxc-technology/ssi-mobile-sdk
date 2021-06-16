package com.dxc.ssi.agent.model.messages

import com.dxc.ssi.agent.model.SharedConnection


data class MessageContext(
    val connection: SharedConnection?,
    val receivedUnpackedMessage: ReceivedUnpackedMessage
)
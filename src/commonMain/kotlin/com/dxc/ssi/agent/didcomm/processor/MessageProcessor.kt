package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.model.messages.Message

interface MessageProcessor {
    suspend fun processMessage(message: Message)

}

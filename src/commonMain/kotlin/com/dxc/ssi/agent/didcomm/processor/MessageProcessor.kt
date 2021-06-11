package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.model.messages.MessageContext

interface MessageProcessor {
    suspend fun processMessage(messageContext: MessageContext)

}

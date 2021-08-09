package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.model.messages.Context

interface MessageProcessor {
    suspend fun processMessage(context: Context)

}

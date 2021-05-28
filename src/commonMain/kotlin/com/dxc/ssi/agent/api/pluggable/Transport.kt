package com.dxc.ssi.agent.api.pluggable

import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.MessageEnvelop

interface Transport {
    suspend fun sendMessage(connection: Connection, message: MessageEnvelop)
    suspend fun receiveNextMessage(): MessageEnvelop
    fun shutdown()

}

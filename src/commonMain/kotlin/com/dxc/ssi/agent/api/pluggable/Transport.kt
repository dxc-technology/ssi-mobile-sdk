package com.dxc.ssi.agent.api.pluggable

import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.ssi.agent.model.messages.MessageEnvelop

interface Transport {
    suspend fun sendMessage(connection: SharedConnection, message: MessageEnvelop)
    suspend fun receiveNextMessage(): MessageEnvelop

}

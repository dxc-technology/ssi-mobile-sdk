package com.dxc.ssi.agent.api.pluggable

import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.MessageEnvelop

interface Transport {
    suspend fun sendMessage(connection: PeerConnection, message: MessageEnvelop)
    suspend fun disconnect(connection: PeerConnection)
    suspend fun receiveNextMessage(): MessageEnvelop
    fun shutdown()

}

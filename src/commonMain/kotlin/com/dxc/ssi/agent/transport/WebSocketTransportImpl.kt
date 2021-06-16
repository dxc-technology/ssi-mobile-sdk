package com.dxc.ssi.agent.transport

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.channels.Channel

//TODO: handle closing websocket correctly
//TODO: cleanup websockets cache with time to avoid memory leak
class WebSocketTransportImpl : Transport {
    private val incomingMessagesChannel: Channel<MessageEnvelop> = Channel()

    private val appSocketThreadSafeProvider = AppSocketThreadSafeProvider(incomingMessagesChannel)


    @OptIn(InternalAPI::class)
    override suspend fun sendMessage(connection: SharedConnection, message: MessageEnvelop) {

        println("Before sending message to endpoint: ${connection.endpoint}")

        if (!(connection.endpoint.protocol == URLProtocol.WS || connection.endpoint.protocol == URLProtocol.WSS))
            throw IllegalArgumentException("Only websockets are supported by WebSocketTransportImpl!")

        val appSocket = appSocketThreadSafeProvider.provideAppSocket(connection.endpoint.toString())

        appSocket.send(message.payload)
    }


    override suspend fun receiveNextMessage(): MessageEnvelop {
        //TODO: ensure that all suspend functions are not blocking. For that use withContext block in the begininng of each suspend fun
        return incomingMessagesChannel.receive()
    }

    override fun shutdown() {
        //TODO: understand what else is needed here
        appSocketThreadSafeProvider.shutdown()
    }

}

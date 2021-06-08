package com.dxc.ssi.agent.transport

import co.touchlab.stately.collections.sharedMutableListOf
import co.touchlab.stately.collections.sharedMutableMapOf
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.delay


//TODO: handle closing websocket correctly
//TODO: cleanup websockets cache with time to avoid memory leak
class WebSocketTransportImpl : Transport {


    //TODO: temporarily using it instead of queue. Need to understand how to use queues/channels?
    //TODO: understand how will it work with concurrency
    private val incomingMessagesQueue = sharedMutableListOf<MessageEnvelop>()
    //TODO: deal with proper closing of sockets
    //private val appSocketsMap = mutableMapOf<String, AppSocket>()

    private val appSocketsMap = sharedMutableMapOf<String, AppSocket>()

    fun init() {


    }

    @OptIn(InternalAPI::class)
    override suspend fun sendMessage(connection: Connection, message: MessageEnvelop) {

        println("Before sending message to endpoint: ${connection.endpoint}")

        if (!(connection.endpoint.protocol == URLProtocol.WS || connection.endpoint.protocol == URLProtocol.WSS))
            throw IllegalArgumentException("Only websockets are supported by WebSocketTransportImpl!")

        val appSocket = openOrGetExistingConnection(connection.endpoint.toString())
        appSocket.send(message.payload)
    }

    private suspend fun openConnection(endpoint: String): AppSocket {
        val appSocket = AppSocket(endpoint, incomingMessagesQueue)
        appSocket.connect()

        return appSocket
    }

    //TODO: make it thread safe
    private suspend fun openOrGetExistingConnection(endpoint: String): AppSocket {

        if (appSocketsMap[endpoint] != null)
            return appSocketsMap[endpoint]!!

        val appSocket = openConnection(endpoint)

        appSocketsMap[endpoint] = appSocket

        return appSocket
    }

    //TODO: decide if we need to leave this call blocking
    override suspend fun receiveNextMessage(): MessageEnvelop {
        //TODO: ensure that all suspend functions are not blocking. For that use withContext block in the begining of each suspend fun

        while (incomingMessagesQueue.size == 0) {
            // Sleeper().sleep(1000)
            delay(1000)
        }

        val message = incomingMessagesQueue[0]
        incomingMessagesQueue.removeAt(0)

        println("Received message $message")

        return message

    }

}

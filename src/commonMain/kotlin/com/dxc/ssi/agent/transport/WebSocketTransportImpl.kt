package com.dxc.ssi.agent.transport

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import io.ktor.util.*
import kotlinx.coroutines.delay
import co.touchlab.stately.collections.sharedMutableMapOf
import co.touchlab.stately.collections.sharedMutableListOf
import co.touchlab.stately.concurrency.Lock
import com.dxc.utils.System
import kotlinx.coroutines.channels.Channel


//TODO: handle closing websocket correctly
//TODO: cleanup websockets cache with time to avoid memory leak
class WebSocketTransportImpl : Transport {
    private val incomingMessagesChannel: Channel<MessageEnvelop> = Channel()

    val appSocketThreadSafeProvider = AppSocketThreadSafeProvider(incomingMessagesChannel)


    @OptIn(InternalAPI::class)
    override suspend fun sendMessage(connection: Connection, message: MessageEnvelop) {

        val host = parseHostFromEndpoint(connection.endpoint)
        val port = parsePortFromEndpoint(connection.endpoint)
        val path = parsePathFromEndpoint(connection.endpoint)
        val protocol = parseProtocolFromEndpoint(connection.endpoint)

        println("host = $host, port = $port, path = $path, protocol = $protocol")

        if (protocol != "ws")
            throw IllegalArgumentException("Only websockets are supported by WebSocketTransportImpl!")

        val url = "$protocol://$host:$port$path"

        println("Synthesized url: $url")
        val appSocket = appSocketThreadSafeProvider.provideAppSocket(url)

        appSocket.send(message.payload)
    }


    //TODO: find some proper URL data model
    //ws://11.0.1.11:7000/ws
    private fun parseProtocolFromEndpoint(endpoint: String): String {
        return Regex("(^.*):\\/\\/.*:.*$").find(endpoint)!!.groups[1]!!.value
    }

    private fun parsePathFromEndpoint(endpoint: String): String {
        return Regex("^.*:.*:.*(\\/.*$)").find(endpoint)!!.groups[1]!!.value

    }

    private fun parsePortFromEndpoint(endpoint: String): Int {
        return Regex("^.*:.*:(.*)\\/.*$").find(endpoint)!!.groups[1]!!.value.toInt()
    }

    private fun parseHostFromEndpoint(endpoint: String): String {
        return Regex("^.*:\\/\\/(.*):.*$").find(endpoint)!!.groups[1]!!.value
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

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


//TODO: handle closing websocket correctly
//TODO: cleanup websockets cache with time to avoid memory leak
class WebSocketTransportImpl : Transport {


    //TODO: temporarily using it instead of queue. Need to understand how to use queues/channels?
    //TODO: understand how will it work with concurrency
    private val incomingMessagesQueue = sharedMutableListOf<MessageEnvelop>()
    //TODO: deal with proper closing of sockets
    //private val appSocketsMap = mutableMapOf<String, AppSocket>()

    private val appSocketsMap = sharedMutableMapOf<String, AppSocket>()
    private val appSocketsMapLock = Lock()


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
        val appSocket = openOrGetExistingConnection(url)
        appSocket.send(message.payload)
    }

    private suspend fun openConnection(endpoint: String): AppSocket {
        val appSocket = AppSocket(endpoint, incomingMessagesQueue)
        appSocket.connect()

        return appSocket
    }


    private suspend fun openOrGetExistingConnection(endpoint: String): AppSocket {
        //TODO: if this locking makes a problem, find better solution here
        appSocketsMapLock.lock()

        if (appSocketsMap[endpoint] != null) {
            println("${System.getCurrentThread()} - appSockets contains such socket")
            appSocketsMapLock.lock()
            return appSocketsMap[endpoint]!!
        }

        println("${System.getCurrentThread()} - opening socket for $endpoint")
        val appSocket = openConnection(endpoint)
        println("${System.getCurrentThread()} - opened socket for $endpoint")
        appSocketsMap[endpoint] = appSocket
        println("${System.getCurrentThread()} - placed websocket in a map")

        appSocketsMapLock.unlock()

        return appSocket
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


    //TODO: decide if we need to leave this call blocking
    override suspend fun receiveNextMessage(): MessageEnvelop {
        //TODO: ensure that all suspend functions are not blocking. For that use withContext block in the begininng of each suspend fun

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

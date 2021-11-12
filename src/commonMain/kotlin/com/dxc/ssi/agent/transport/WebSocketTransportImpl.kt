package com.dxc.ssi.agent.transport

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.exceptions.transport.MessageCouldNotBeDeliveredException
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import io.ktor.util.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger

//TODO: handle closing websocket correctly
//TODO: cleanup websockets cache with time to avoid memory leak
class WebSocketTransportImpl(val ip: String, val port: Int) : Transport {

    //TODO: think about making it configurable
    private val maxNumberOfRetries = 5
    private val initialDelay = 1000L

    private val incomingMessagesChannel: Channel<MessageEnvelop> = Channel()
    private val appSocketThreadSafeProvider = AppSocketThreadSafeProviderDuplex(incomingMessagesChannel, ip, port)
    var logger: Kermit = Kermit(LogcatLogger())

    @OptIn(InternalAPI::class)
    override suspend fun sendMessage(connection: PeerConnection, message: MessageEnvelop) {
        logger.d { "Before sending message to endpoint: ${connection.endpoint}" }
//        if (!(connection.endpoint.protocol == URLProtocol.WS || connection.endpoint.protocol == URLProtocol.WSS))
//            throw IllegalArgumentException("Only websockets are supported by WebSocketTransportImpl!")


        var numberOfRetries = 0

        while (true) {
            try {
                val appSocket = appSocketThreadSafeProvider.provideAppSocket(connection.endpoint.toString())
                appSocket.send(message.payload)
                break
            } catch (t: Throwable) {
                //TODO: check somewhere if connection already abandoned, do not retry
                numberOfRetries++
                appSocketThreadSafeProvider.disconnectAndDropAppSocket(connection.endpoint.toString())
                logger.d { "Error happened while sending message" }
                if (numberOfRetries == maxNumberOfRetries) {
                    logger.d {"Retry limit exceeded. Won't try" }
                    throw MessageCouldNotBeDeliveredException("Could not deliver message $message after $maxNumberOfRetries retires")
                }
                delay(initialDelay * numberOfRetries)
                logger.d { "Reconnecting..." }

            }
        }
    }


    override suspend fun disconnect(connection: PeerConnection) {
        appSocketThreadSafeProvider.disconnectAndDropAppSocket(connection.endpoint.toString())
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

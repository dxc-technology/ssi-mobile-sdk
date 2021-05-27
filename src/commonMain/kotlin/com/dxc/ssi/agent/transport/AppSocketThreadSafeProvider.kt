package com.dxc.ssi.agent.transport

import co.touchlab.stately.collections.sharedMutableMapOf
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.utils.System
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class AppSocketThreadSafeProvider(private val incomingMessagesChannel: Channel<MessageEnvelop>) {
    //TODO: looks like we need to deal with handling of closed sockets and with reconnects
    data class GetAppSocketMessage(
        val endpoint: String,
        val appSocket: CompletableDeferred<AppSocket>
    )

    private val appSocketsMap = sharedMutableMapOf<String, AppSocket>()
    private val channel = Channel<GetAppSocketMessage>()

    suspend fun provideAppSocket(endpoint: String): AppSocket {
        println("AppSocketThreadSafeProvider - socket for $endpoint requested")
        val appSocket = CompletableDeferred<AppSocket>()
        channel.send(GetAppSocketMessage(endpoint, appSocket))
        return appSocket.await()
    }

    init {
        //TODO: add cancellation here
        CoroutineScope(Dispatchers.Default).launch {
            processRequests()
        }

    }

    private suspend fun processRequests() {
        for (msg in channel) {
            val endpoint = msg.endpoint
            if (appSocketsMap[endpoint] == null) {
                println("${System.getCurrentThread()} - opening socket for $endpoint")
                val appSocket = openConnection(endpoint)
                println("${System.getCurrentThread()} - opened socket for $endpoint")
                appSocketsMap[endpoint] = appSocket
                println("${System.getCurrentThread()} - placed websocket in a map")
            }
            msg.appSocket.complete(appSocketsMap[endpoint]!!)
        }
    }


    private suspend fun openConnection(endpoint: String): AppSocket {
        val appSocket = AppSocket(endpoint, incomingMessagesChannel)
        appSocket.connect()
        return appSocket
    }

}
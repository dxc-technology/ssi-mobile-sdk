package com.dxc.ssi.agent.transport

import co.touchlab.stately.collections.sharedMutableMapOf
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.utils.System
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class AppSocketThreadSafeProvider(private val incomingMessagesChannel: Channel<MessageEnvelop>) {

    private var job = Job()
    private val providerScope = CoroutineScope(Dispatchers.Default + job)

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
        providerScope.launch {
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

    fun shutdown() {
        //TODO: see what else is needed here
        job.cancel()
    }

}
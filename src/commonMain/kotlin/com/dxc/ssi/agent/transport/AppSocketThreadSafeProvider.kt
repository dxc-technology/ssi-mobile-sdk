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
    ) : ControlMessage

    data class DisposeAppSocketMessage(
        val endpoint: String,
        val disposalStatus: CompletableDeferred<Unit>
    ) : ControlMessage

    private val appSocketsMap = sharedMutableMapOf<String, AppSocket>()
    private val channel = Channel<ControlMessage>()

    suspend fun provideAppSocket(endpoint: String): AppSocket {
        println("AppSocketThreadSafeProvider - socket for $endpoint requested")
        val appSocket = CompletableDeferred<AppSocket>()
        channel.send(GetAppSocketMessage(endpoint, appSocket))
        return appSocket.await()
    }

    suspend fun disconnectAndDropAppSocket(endpoint: String) {
        println("AppSocketThreadSafeProvider - removal of socket socket for $endpoint requested")
        val disposalStatus = CompletableDeferred<Unit>()
        channel.send(DisposeAppSocketMessage(endpoint, disposalStatus))
    }

    init {
        providerScope.launch {
            processRequests()
        }
    }

    private suspend fun processRequests() {
        for (msg in channel) {
            when (msg) {
                is GetAppSocketMessage -> {
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

                is DisposeAppSocketMessage -> {
                    val endpoint = msg.endpoint

                    println("Received dispose message for $endpoint")

                    if (appSocketsMap[endpoint] != null) {
                        val appSocket = appSocketsMap[endpoint]!!
                        //TODO: do we need to handle case if socket can not be closed or it is impossible?
                        //TODO: thre is a bug currently, when we disconnect, there is an exception in logs. Ignoring it for now, but will need to raise a bug
                        appSocket.disconnect()
                        appSocketsMap.remove(endpoint)
                        println("Disconnected and removed WS from map for $endpoint")
                    }
                    msg.disposalStatus.complete(Unit)
                }
            }
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

    interface ControlMessage
}
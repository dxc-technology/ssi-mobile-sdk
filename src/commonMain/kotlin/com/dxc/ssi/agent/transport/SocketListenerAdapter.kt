package com.dxc.ssi.agent.transport

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

//TODO: see if we can avoid this adapter or join it with PlatformSocketListener somehow
class SocketListenerAdapter(
    //TODO: replace with CompletableDeferred?
    val socketOpenedChannel: Channel<Unit> = Channel(),
    val socketReceivedMessageChannel: Channel<String> = Channel(),
    val socketFailureChannel: Channel<Throwable> = Channel(),
    val socketClosedChannel: Channel<SocketClosureDetails> = Channel()
) {
    fun onOpened() {
        runBlocking {
            socketOpenedChannel.send(Unit)
        }

    }

    fun onMessageReceived(message: String)  {
        println("received message: $message")
        runBlocking {
            socketReceivedMessageChannel.send(message)
        }
    }

    fun onFailure(throwable: Throwable) {
        runBlocking {
            println("Encountered socket error")

            socketFailureChannel.send(throwable)
        }
    }

    fun onClosed(socketClosureDetails: SocketClosureDetails) {
        runBlocking {
            println("Socket closed")
            socketClosedChannel.send(
                socketClosureDetails
            )
        }

    }

}
package com.dxc.ssi.agent.transport

import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.utils.System
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class AppSocket(url: String, incomingMessagesChannel: Channel<MessageEnvelop>) {
    private val ws = PlatformSocket(url, "",0)
    private val job: CompletableJob = Job()
    private val logger: Kermit = Kermit(LogcatLogger())

    var socketError: Throwable?
        get() = isoSocketError.access { it.obj }!!
        private set(value) {
            isoSocketError.access { it.obj = value }
        }

    var currentState: State
        get() = isoCurrentState.access { it.obj }!!
        set(value) {
            isoCurrentState.access { it.obj = value }
        }
    private val isoCurrentState = IsolateState { ObjectHolder(State.CLOSED) }
    private val isoSocketError = IsolateState { ObjectHolder<Throwable?>(null) }

    private val socketListener: PlatformSocketListener = object : PlatformSocketListener {
        override fun onOpen() {

            currentState = State.CONNECTED

            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async {
                socketOpenedChannel.send(SocketOpenedMessage())
            })
            logger.d { "PlatformSocketListener: ${System.getCurrentThread()} - Opened socket" }

        }

        override fun onFailure(t: Throwable) {
            socketError = t
            currentState = State.CLOSED
            logger.d { "PlatformSocketListener: Socket failure: $t \n ${t.stackTraceToString()}" }
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async {
                socketOpenedChannel.send(SocketFailureMessage())
            })

        }

        override fun onMessage(msg: String) {
            logger.d { "${System.getCurrentThread()} - Received message: $msg" }
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async {
                incomingMessagesChannel.send(MessageEnvelop(msg))
            })

        }

        override fun onClosing(code: Int, reason: String) {
            currentState = State.CLOSING
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async { socketClosingChannel.send(Unit) })
            logger.d { "PlatformSocketListener: Closing socket: code = $code, reason = $reason" }
        }

        override fun onClosed(code: Int, reason: String) {
            currentState = State.CLOSED

            logger.d { "PlatformSocketListener: Closed socket: code = $code, reason = $reason" }
            job.complete()
        }
    }

    val socketOpenedChannel: Channel<AppSocketMessage> = Channel()
    val socketClosingChannel: Channel<Unit> = Channel()

    suspend fun connect() {
        if (currentState != State.CLOSED) {
            throw IllegalStateException("The socket is available.")
        }
        socketError = null
        currentState = State.CONNECTING


        ws.openSocket(socketListener)

        logger.d { "Thread = ${System.getCurrentThread()} awaiting while websocket is opened" }
        when (socketOpenedChannel.receive()) {
            is SocketOpenedMessage -> {
                logger.d { "After socketListener.onOpen" }

                if (currentState != State.CONNECTED)
                    throw IllegalStateException("Could not be opened")
            }
            is SocketFailureMessage -> {
                //TODO: make the exception more meaningful allowing to differentiate between different types of errors
                throw IllegalStateException("Could not be opened")
            }

        }

    }

    suspend fun disconnect() {
        if (currentState != State.CLOSED) {
            currentState = State.CLOSING
            ws.closeSocket(1000, "The user has closed the connection.")
            //TODO: if we try to do it it hangs currently
            //socketClosingChannel.receive()
        }
    }

    fun send(msg: String) {
        if (currentState != State.CONNECTED) throw IllegalStateException("The connection is lost.")
        logger.d { "Sending message to websocket" }
        ws.sendMessage(msg)
        logger.d { "Sent message to websocket" }
    }

    enum class State {
        CONNECTING,
        CONNECTED,
        CLOSING,
        CLOSED
    }

    interface AppSocketMessage

    class SocketOpenedMessage : AppSocketMessage
    class SocketFailureMessage : AppSocketMessage
}
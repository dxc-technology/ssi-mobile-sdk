package com.dxc.ssi.agent.transport

import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.utils.System
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class AppSocket(url: String, incomingMessagesChannel: Channel<MessageEnvelop>) {
    private val ws = PlatformSocket(url)
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
            logger.log(Severity.Debug,"",null) { "PlatformSocketListener: ${System.getCurrentThread()} - Opened socket" }

        }

        override fun onFailure(t: Throwable) {
            socketError = t
            currentState = State.CLOSED
            logger.log(Severity.Debug,"",null) { "PlatformSocketListener: Socket failure: $t \n ${t.stackTraceToString()}" }
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async {
                socketOpenedChannel.send(SocketFailureMessage())
            })

        }

        override fun onMessage(msg: String) {
            logger.log(Severity.Debug,"",null) { "${System.getCurrentThread()} - Received message: $msg" }
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async {
                incomingMessagesChannel.send(MessageEnvelop(msg))
            })

        }

        override fun onClosing(code: Int, reason: String) {
            currentState = State.CLOSING
            CoroutineHelper.waitForCompletion(CoroutineScope(Dispatchers.Default).async { socketClosingChannel.send(Unit) })
            logger.log(Severity.Debug,"",null) { "PlatformSocketListener: Closing socket: code = $code, reason = $reason" }
        }

        override fun onClosed(code: Int, reason: String) {
            currentState = State.CLOSED

            logger.log(Severity.Debug,"",null) { "PlatformSocketListener: Closed socket: code = $code, reason = $reason" }
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

        logger.log(Severity.Debug,"",null) { "Thread = ${System.getCurrentThread()} awaiting while websocket is opened" }
        when (socketOpenedChannel.receive()) {
            is SocketOpenedMessage -> {
                logger.log(Severity.Debug,"",null) { "After socketListener.onOpen" }

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
        logger.log(Severity.Debug,"",null) { "Sending message to websocket" }
        ws.sendMessage(msg)
        logger.log(Severity.Debug,"",null) { "Sent message to websocket" }
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
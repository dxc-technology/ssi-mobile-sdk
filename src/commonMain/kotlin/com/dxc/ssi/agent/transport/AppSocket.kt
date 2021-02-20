package com.dxc.ssi.agent.transport

import com.dxc.ssi.agent.model.messages.MessageEnvelop

//Common
class AppSocket(url: String, incomingMessagesQueue: MutableList<MessageEnvelop>) {
    private val ws = PlatformSocket(url)
    var socketError: Throwable? = null
        private set
    var currentState: State = State.CLOSED
        private set(value) {
            field = value
            stateListener?.invoke(value)
        }
    var stateListener: ((State) -> Unit)? = null
        set(value) {
            field = value
            value?.invoke(currentState)
        }


    //TODO: rework this function to be more robust and more suited for different platforms
    fun connect() {
        if (currentState != State.CLOSED) {
            throw IllegalStateException("The socket is available.")
        }
        socketError = null
        currentState = State.CONNECTING
        ws.openSocket(socketListener)

        while (currentState == State.CONNECTING) {
            Sleeper().sleep(100)
        }

        if (currentState != State.CONNECTED)
            throw throw IllegalStateException("Could not be opened")

    }

    fun disconnect() {
        if (currentState != State.CLOSED) {
            currentState = State.CLOSING
            ws.closeSocket(1000, "The user has closed the connection.")
        }
    }

    fun send(msg: String) {
        if (currentState != State.CONNECTED) throw IllegalStateException("The connection is lost.")
        println("Sending message to websocket")
        ws.sendMessage(msg)
        println("Sent message to websocket")
    }

    private val socketListener:PlatformSocketListener = object : PlatformSocketListener {
        override fun onOpen() {
            println("Opened socket")
            currentState = State.CONNECTED
        }

        override fun onFailure(t: Throwable) {
            socketError = t
            currentState = State.CLOSED
            println("Socket failure: $t \n ${t.stackTraceToString()}")
        }

        override fun onMessage(msg: String) {
            println("Received message: $msg")
            incomingMessagesQueue.add(MessageEnvelop(msg))

        }

        override fun onClosing(code: Int, reason: String) {
            currentState = State.CLOSING
            println("Closing socket: code = $code, reason = $reason")
        }

        override fun onClosed(code: Int, reason: String) {
            currentState = State.CLOSED
            println("Closed socket: code = $code, reason = $reason")
        }
    }

    enum class State {
        CONNECTING,
        CONNECTED,
        CLOSING,
        CLOSED
    }
}
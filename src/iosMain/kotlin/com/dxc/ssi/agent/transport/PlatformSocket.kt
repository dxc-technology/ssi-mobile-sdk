package com.dxc.ssi.agent.transport

import co.touchlab.stately.freeze
import co.touchlab.stately.isolate.IsolateState
import platform.Foundation.*
import platform.darwin.NSObject
import kotlin.native.concurrent.isFrozen


//import Starscream


data class WebSocketWrapper(var websocket: NSURLSessionWebSocketTask? = null)
internal actual class PlatformSocket actual constructor(url: String) {
    private val socketEndpoint = NSURL.URLWithString(url)!!

    // private var webSocket: NSURLSessionWebSocketTask? = null
    private val isolatedWebSocket = IsolateState { WebSocketWrapper() }

    //: IsolateState<NSURLSessionWebSocketTask> = IsolateState(null)
    actual fun openSocket(socketListenerAdapter: SocketListenerAdapter) {
        val urlSession = NSURLSession.sessionWithConfiguration(
            configuration = NSURLSessionConfiguration.defaultSessionConfiguration(),
            delegate = object : NSObject(), NSURLSessionWebSocketDelegateProtocol {
                override fun URLSession(
                    session: NSURLSession,
                    webSocketTask: NSURLSessionWebSocketTask,
                    didOpenWithProtocol: String?
                ) {
                    socketListenerAdapter.onOpened()


                }

                override fun URLSession(
                    session: NSURLSession,
                    webSocketTask: NSURLSessionWebSocketTask,
                    didCloseWithCode: NSURLSessionWebSocketCloseCode,
                    reason: NSData?
                ) {

                    socketListenerAdapter.onClosed(
                        SocketClosureDetails(
                            didCloseWithCode.toInt(),
                            reason.toString()
                        )
                    )


                }
            }.freeze(),
            delegateQueue = NSOperationQueue.currentQueue()
        )

        println("PlatformSocket.isFrozen = ${this.isFrozen}")


        isolatedWebSocket.access {
            it.websocket = urlSession.webSocketTaskWithURL(socketEndpoint)


        }

        listenMessages(socketListenerAdapter)
        isolatedWebSocket.access { it.websocket?.resume() }

    }

    private fun listenMessages(socketListenerAdapter: SocketListenerAdapter) {
        println("PlatformSocket: in listenMessages")

        isolatedWebSocket.access {
            println("PlatformSocket: accessed isolatedWebsocket")

            val receiverHandler = { message: NSURLSessionWebSocketMessage?, nsError: NSError? ->
                when {
                    nsError != null -> {

                        socketListenerAdapter.onFailure(Throwable(nsError.description))
                    }
                    message != null -> {
                        message.string?.let {
                            println("PlatformSocket: received text message $it")
                            socketListenerAdapter.onMessageReceived(it)
                        }
                    }
                }
                listenMessages(socketListenerAdapter)
            }


            println("PlatformSocket: constructed receiverHandler")
            it.websocket?.receiveMessageWithCompletionHandler(receiverHandler.freeze())
        }
    }

    actual fun closeSocket(code: Int, reason: String) {
        isolatedWebSocket.access { it.websocket?.cancelWithCloseCode(code.toLong(), null) }
        isolatedWebSocket.access { it.websocket = null }
    }

    actual fun sendMessage(msg: String) {
        val message = NSURLSessionWebSocketMessage(msg)

        val completionHandler: (platform.Foundation.NSError?) -> kotlin.Unit = { err: NSError? ->
            err?.let { println("send $msg error: $it") }
        }

        println("In platform sendMessage")
        isolatedWebSocket.access { it.websocket!!.sendMessage(message, completionHandler.freeze()) }
    }
}
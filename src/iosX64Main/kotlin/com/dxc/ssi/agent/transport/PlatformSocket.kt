package com.dxc.ssi.agent.transport

import co.touchlab.stately.freeze
import co.touchlab.stately.isolate.IsolateState
import kotlinx.coroutines.runBlocking
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
    actual fun openSocket(/*listener: PlatformSocketListener, */socketListenerLoosingAdapter: SocketListenerLoosingAdapter) {
        val urlSession = NSURLSession.sessionWithConfiguration(
            configuration = NSURLSessionConfiguration.defaultSessionConfiguration(),
            delegate = object : NSObject(), NSURLSessionWebSocketDelegateProtocol {
                override fun URLSession(
                    session: NSURLSession,
                    webSocketTask: NSURLSessionWebSocketTask,
                    didOpenWithProtocol: String?
                ) {
                    runBlocking {
                        socketListenerLoosingAdapter.socketOpenedChannel.send(Unit)
                    }
                }

                override fun URLSession(
                    session: NSURLSession,
                    webSocketTask: NSURLSessionWebSocketTask,
                    didCloseWithCode: NSURLSessionWebSocketCloseCode,
                    reason: NSData?
                ) {
                    runBlocking {
                        println("Socket closed")
                        socketListenerLoosingAdapter.socketClosedChannel.send(
                            SocketClosureDetails(
                                didCloseWithCode.toInt(),
                                reason.toString()
                            )
                        )
                    }

                }
            }.freeze(),
            delegateQueue = NSOperationQueue.currentQueue()
        )

        println("PlatformSocket.isFrozen = ${this.isFrozen}")


        isolatedWebSocket.access {
            it.websocket = urlSession.webSocketTaskWithURL(socketEndpoint)
            //  listenMessages(socketListenerLoosingAdapter)
            // it.websocket?.resume()

        }

        listenMessages(socketListenerLoosingAdapter)
        isolatedWebSocket.access { it.websocket?.resume() }

    }

    private fun listenMessages(socketListenerLoosingAdapter: SocketListenerLoosingAdapter) {
        isolatedWebSocket.access {


            val receiverHandler = { message: NSURLSessionWebSocketMessage?, nsError: NSError? ->
                when {
                    nsError != null -> {
                        runBlocking {
                            println("Encountered socket error")
                            socketListenerLoosingAdapter.socketFailureChannel.send(Throwable(nsError.description))
                        }

                    }
                    message != null -> {
                        message.string?.let {
                            println("received message: $it")
                            runBlocking {
                                socketListenerLoosingAdapter.socketReceivedMessageChannel.send(it)
                            }
                        }
                    }
                }
                listenMessages(socketListenerLoosingAdapter)
            }



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
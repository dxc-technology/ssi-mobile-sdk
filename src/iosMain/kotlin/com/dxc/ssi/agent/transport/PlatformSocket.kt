package com.dxc.ssi.agent.transport

import co.touchlab.stately.isolate.IsolateState
import cocoapods.PocketSocket.PSWebSocket
import cocoapods.PocketSocket.PSWebSocketDelegateProtocol
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.darwin.NSInteger
import platform.darwin.NSObject
import platform.darwin.dispatch_queue_create
import platform.posix.sleep
import kotlin.native.concurrent.isFrozen


data class WebSocketWrapper(var websocket: PSWebSocket? = null)

internal actual class PlatformSocket actual constructor(url: String) : NSObject(), PSWebSocketDelegateProtocol {
    private val socketEndpoint = NSURL.URLWithString(url)!!
    private val isolatedWebSocket = IsolateState { WebSocketWrapper() }
    private var psl: PlatformSocketListener? = null
    actual fun openSocket(
        platformSocketListener: PlatformSocketListener
    ) {
        psl = platformSocketListener
        println("PlatformSocket.isFrozen = ${this.isFrozen}")

        isolatedWebSocket.access {
            val request = NSURLRequest.requestWithURL(socketEndpoint)
            println(socketEndpoint)
            it.websocket =
                PSWebSocket.clientSocketWithRequest(request)
            it.websocket?.delegateQueue = dispatch_queue_create(null, null)
            it.websocket?.delegate = this
            it.websocket?.open()
        }

        isolatedWebSocket.access {
            val status = it.websocket?.readyState
            println(status)
        }
        sleep(1)

        println("PlatformSocket: constructed receiverHandler")
    }
    actual fun closeSocket(code: Int, reason: String) {
        isolatedWebSocket.access { it.websocket?.closeWithCode(code.toLong(), null) }
        isolatedWebSocket.access { it.websocket = null }
    }

    actual fun sendMessage(msg: String) {
        println("In platform sendMessage")
        isolatedWebSocket.access {
            it.websocket!!.send(msg)
        }
    }

    override fun webSocket(webSocket: PSWebSocket?, didFailWithError: NSError?) {
        println("PlatformSocket: error")
        psl?.onFailure(Throwable(didFailWithError?.description))
    }

    override fun webSocket(webSocket: PSWebSocket?, didReceiveMessage: Any?) {
        println("PlatformSocket: message")
        psl?.onMessage(didReceiveMessage.toString())
    }

    override fun webSocket(webSocket: PSWebSocket?, didCloseWithCode: NSInteger, reason: String?, wasClean: Boolean) {
        println("PlatformSocket: closed")
        psl?.onClosed(didCloseWithCode.toInt(), reason.toString())
    }

    override fun webSocketDidOpen(webSocket: PSWebSocket?) {
        println("PlatformSocket: open")
        psl?.onOpen()
    }
}


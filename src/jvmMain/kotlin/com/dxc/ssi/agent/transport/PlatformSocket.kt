package com.dxc.ssi.agent.transport


import okhttp3.*
import okio.ByteString.Companion.encodeUtf8
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage
import org.eclipse.jetty.websocket.server.WebSocketUpgradeFilter
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse
import org.eclipse.jetty.websocket.servlet.WebSocketCreator
import sun.security.krb5.Confounder.bytes
import java.net.InetAddress
import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets


private var pfl: PlatformSocketListener? = null

internal actual class PlatformSocket actual constructor(url: String) {
    private val socketEndpoint = url
    private var webSocket: WebSocket? = null
//    actual fun openSocket(platformSocketListener: PlatformSocketListener) {
//        val socketRequest = Request.Builder().url(socketEndpoint).build()
//        val webClient = OkHttpClient().newBuilder().build()
//        webSocket = webClient.newWebSocket(
//            socketRequest,
//            object : WebSocketListener() {
//                override fun onOpen(webSocket: WebSocket, response: Response) = platformSocketListener.onOpen()
//                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) =
//                    platformSocketListener.onFailure(t)
//
//                override fun onMessage(webSocket: WebSocket, text: String) =
//                    platformSocketListener.onMessage(text)
//
//                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) =
//                    platformSocketListener.onClosing(code, reason)
//
//                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) =
//                    platformSocketListener.onClosed(code, reason)
//            }
//        )
//
//
//    }

    actual fun openSocket(platformSocketListener: PlatformSocketListener) {
        val socketRequest = Request.Builder().url(socketEndpoint).build()
        val webClient = OkHttpClient().newBuilder().build()
        webSocket = webClient.newWebSocket(
            socketRequest,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) = platformSocketListener.onOpen()
                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) =
                    platformSocketListener.onFailure(t)

                override fun onMessage(webSocket: WebSocket, text: String) =
                    platformSocketListener.onMessage(text)

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) =
                    platformSocketListener.onClosing(code, reason)

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) =
                    platformSocketListener.onClosed(code, reason)
            }
        )
        pfl = platformSocketListener
        val runnable = Runnable {
            val isa = InetSocketAddress(InetAddress.getByName("192.168.0.104"), 8123)
            val server = Server(isa)
            val context = ServletContextHandler(ServletContextHandler.SESSIONS)
            context.contextPath = "/"
            server.handler = context
            val wsFilter: WebSocketUpgradeFilter = WebSocketUpgradeFilter.configureContext(context)
            wsFilter.addMapping("/ws", SocketCreator())
            server.start()
            server.join()
        }
        val thread = Thread(runnable)
        try {
            thread.start()
            platformSocketListener.onOpen()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    actual fun closeSocket(code: Int, reason: String) {
        webSocket?.close(code, reason)
        webSocket = null

    }

    actual fun sendMessage(msg: String) {
        val convertedMsg = msg.encodeUtf8()
        webSocket?.send(convertedMsg)
    }
}
@org.eclipse.jetty.websocket.api.annotations.WebSocket
class JettyWebSocket {
    @OnWebSocketConnect
    fun onOpen(session: Session?) {
        println("onOpen")
        pfl?.onOpen()
    }

    @OnWebSocketMessage
    fun onMessage(buf :ByteArray, offset:Int, lenght:Int) {
        println("")
        println(">>>>>>>>>>>>>")
        println(buf)
        println(offset)
        println(lenght)
        val message = String(buf, StandardCharsets.UTF_8)
        print(message)
        pfl?.onMessage(message)
    }

    @OnWebSocketClose
    fun onClose(closeCode: Int, closeReasonPhrase: String?) {
        println("onClose")
        println("$closeCode $closeReasonPhrase")
        pfl?.onClosed(closeCode,closeReasonPhrase!!)
    }
}
class SocketCreator : WebSocketCreator {
    override fun createWebSocket(req: ServletUpgradeRequest, resp: ServletUpgradeResponse): Any {
        return JettyWebSocket()
    }
}
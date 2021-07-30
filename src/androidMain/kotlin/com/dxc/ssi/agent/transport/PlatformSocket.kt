package com.dxc.ssi.agent.transport


import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket

internal actual class PlatformSocket actual constructor(url: String) {
    private val socketEndpoint = url
    private var webSocket: WebSocket? = null
    actual fun openSocket(platformSocketListener: PlatformSocketListener) {
        val kermit = Kermit(LogcatLogger(), CommonLogger())
        kermit.i("CustomTag") { "Message" }
        val socketRequest = Request.Builder().url(socketEndpoint).build()
        val webClient = OkHttpClient().newBuilder().build()
        webSocket = webClient.newWebSocket(
            socketRequest,
            object : okhttp3.WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) = platformSocketListener.onOpen()
                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) =
                    platformSocketListener.onFailure(t)

                override fun onMessage(webSocket: WebSocket, text: String) =
                    platformSocketListener.onMessage(text)

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) =
                    platformSocketListener.onClosed(code, reason)

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) =
                    platformSocketListener.onClosed(code, reason)
            }
        )


    }

    actual fun closeSocket(code: Int, reason: String) {
        webSocket?.close(code, reason)
        webSocket = null
    }

    actual fun sendMessage(msg: String) {
        webSocket?.send(msg)
    }
}
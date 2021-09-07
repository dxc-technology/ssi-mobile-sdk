package com.dxc.ssi.agent.transport


import okhttp3.*

internal actual class PlatformSocket actual constructor(url: String) {
    private val socketEndpoint = url
    private var webSocket: WebSocket? = null
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


    }

    actual fun closeSocket(code: Int, reason: String) {
        webSocket?.close(code, reason)
        webSocket = null

    }

    actual fun sendMessage(msg: String) {
        webSocket?.send(msg)
    }
}
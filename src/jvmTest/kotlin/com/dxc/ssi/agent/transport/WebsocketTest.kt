package com.dxc.ssi.agent.transport

import com.dxc.utils.Sleeper
import okhttp3.*
import org.junit.Test

class WebsocketTest {

    val socketEndpoint = "ws://192.168.0.117:9000/ws"

    @Test
    fun testWs() {
        val socketRequest = Request.Builder().url(socketEndpoint).build()
        val webClient = OkHttpClient().newBuilder().build()
        var webSocket : WebSocket? = webClient.newWebSocket(
            socketRequest,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    println("Opened websocket")
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    println("Failure websocket")
                }


                override fun onMessage(webSocket: WebSocket, text: String) {
                    println("OnMessage")
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    println("Closing websocket")
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    println("Closed websocket")
                }

            }
        )

        Sleeper().sleep(2000)

        webSocket?.close(1000, "The user has closed the connection.")

        webSocket = null

        Sleeper().sleep(10000)

    }
}
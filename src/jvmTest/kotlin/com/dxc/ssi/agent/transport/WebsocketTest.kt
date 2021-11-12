package com.dxc.ssi.agent.transport

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.utils.Sleeper
import okhttp3.*
import org.junit.Test

class WebsocketTest {

    val socketEndpoint = "ws://192.168.0.104:8123/ws"

    @Test
    fun testWs() {
        var logger: Kermit = Kermit(LogcatLogger())
        val socketRequest = Request.Builder().url(socketEndpoint).build()
        val webClient = OkHttpClient().newBuilder().build()
        var webSocket : WebSocket? = webClient.newWebSocket(
            socketRequest,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    logger.d { "Opened websocket" }
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    logger.d { "Failure websocket"+ t.message }
                }


                override fun onMessage(webSocket: WebSocket, text: String) {
                    logger.d { "OnMessage" }
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    logger.d { "Closing websocket" }
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    logger.d { "Closed websocket" }
                }

            }
        )

        Sleeper().sleep(2000)

        webSocket?.close(1000, "The user has closed the connection.")

        webSocket = null

        Sleeper().sleep(10000)

    }
}
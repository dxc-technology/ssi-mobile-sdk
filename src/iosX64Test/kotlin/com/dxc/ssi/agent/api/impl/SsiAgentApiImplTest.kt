package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.transport.Sleeper

import kotlin.test.Test
import kotlin.test.Ignore

class SsiAgentApiImplTest {

    @Test
    @Ignore
    fun basicTest() {

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .build()

        ssiAgentApi.init()

        val invitationUrl =
            "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkhyWGJ5bTRXc2o1cktvb3RUUDRNa1hxWkV2dnR3ZVJ5WEtYNFRuOHdYcFZNIl0sInJlY2lwaWVudEtleXMiOlsiR1JjcTRiWmd0QWRadXhDUEJ0SERCZ2duUXdKTk1MY1EzVHFydTF6dXRZWE0iXSwiQGlkIjoiMDcwZmI2ZmEtZTE1ZC00MjA4LTlmNWItNmYxNjllODYyNzg2IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"
         // "ws://192.168.0.104:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjEwNDo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiR0ZXNkJGalFNc1FXTFFHMzFGNjh2Uzk5TVg5UUtSV1pFZGZQNmJRaHEzNlgiXSwicmVjaXBpZW50S2V5cyI6WyJIY0ZFVllkc3JwaVJpaXVMcXlrTTNWSFRaUlBXSmJwTWt3RlRpWVhMTndROSJdLCJAaWQiOiI0NWY5ZDAyOC1mMjg0LTRkN2MtYTYwYy0yODkwNWVjOTk4MmMiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
        ssiAgentApi.connect(invitationUrl)

        Sleeper().sleep(100000)

    }

    class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: Connection,
            endpoint: String,
            invitation: Invitation
        ): CallbackResult {
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(connection: Connection, request: ConnectionRequest): CallbackResult {
            println("Request sent hook called : $connection, $request")
            return CallbackResult(true)
        }

        override fun onResponseReceived(connection: Connection, response: ConnectionResponse): CallbackResult {
            println("Response received hook called : $connection, $response")
            return CallbackResult(true)
        }

        override fun onCompleted(connection: Connection): CallbackResult {
            println("Connection completed : $connection")
            return CallbackResult(true)
        }

    }
}
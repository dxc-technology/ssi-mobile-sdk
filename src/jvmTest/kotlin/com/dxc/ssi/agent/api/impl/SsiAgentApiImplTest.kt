package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.transport.Sleeper
import org.junit.Ignore
import org.junit.Test

class SsiAgentApiImplTest {


    @Test
    @Ignore("Ignored because it is actually integration tets whoch should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .build()

        ssiAgentApi.init()

        val invitationUrl =
            "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIjVyUzNZSHp0R1czVXpxc25YN1Zwams3d29hOVVzQzVUY1djcWVjY1FBMkh0Il0sInJlY2lwaWVudEtleXMiOlsiNE5HWkZ2WFY3cDYyZDdqWXFjN2hVZWpOekJVOWMxRVJIOTVBcU5mN0VQaFMiXSwiQGlkIjoiOGQxZGNmN2YtNzliOC00OTgwLWFjNzItYzY4N2EzMGZmMTQ5IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"


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
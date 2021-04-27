package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.transport.Sleeper
import com.dxc.ssi.agent.utils.ToBeReworked
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.internal.test.testLauncherEntryPoint

import kotlin.test.Test
import kotlin.test.Ignore

class SsiAgentApiImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Ignore
    fun basicTest() {

        // ToBeReworked.enableIndyLog()

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .build()

        ssiAgentApi.init()

        val invitationUrl =
            "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbInNZeGRmaGh0bXJBWGF0WmhSWVZvUktNcDloVGJLZ2hmTkNCZ3Ntc0VYRngiXSwicmVjaXBpZW50S2V5cyI6WyI4MzF0N2o5VGF0MkZqd3lRM1M1WXlNc3VjMVdMYnlla044R1hFTGhEcDJxRiJdLCJAaWQiOiIzNGZiNTY3Ni1hYTRmLTRmNmItYjg4Yi03NTE1ZWQ4YTNmNTgiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
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
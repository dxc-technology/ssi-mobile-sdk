package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.didcomm.model.common.ProblemReport
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection

class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
    override fun onInvitationReceived(
        connection: Connection,
        endpoint: String,
        invitation: Invitation
    ): CallbackResult {
        println("Invitation received hook called : $connection, $invitation")
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

    override fun onAbandoned(connection: Connection, problemReport: ProblemReport): CallbackResult {
        println("Connection abandoned : $connection")
        return CallbackResult(true)
    }

}
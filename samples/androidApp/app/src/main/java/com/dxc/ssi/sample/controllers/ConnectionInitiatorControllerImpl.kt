package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.DidExchangeError
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.model.PeerConnection


class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {

    override fun onRequestSent(connection: PeerConnection, request: ConnectionRequest): CallbackResult {
        println("Request sent hook called : $connection, $request")
        return CallbackResult(true)
    }

    override fun onResponseReceived(
        connection: PeerConnection,
        response: ConnectionResponse
    ): CallbackResult {
        println("Response received hook called : $connection, $response")
        return CallbackResult(true)
    }

    override fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?): CallbackResult {
        println("Connection abandoned : $connection")
        return CallbackResult(true)
    }

    override fun onCompleted(connection: PeerConnection): CallbackResult {
        println("Connection completed : $connection")
        return CallbackResult(true)
    }

    override fun onFailure(
        connection: PeerConnection?,
        error: DidExchangeError,
        message: String?,
        details: String?,
        stackTrace: String?
    ) {
        TODO("Not yet implemented")
    }

    override fun onInvitationReceived(connection: PeerConnection, invitation: Invitation): CallbackResult {
        println("Invitation received hook called : $connection, $invitation")
        return CallbackResult(canProceedFurther = true)
    }

}
package com.dxc.ssi.agent.api.callbacks.didexchange

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.model.PeerConnection

interface ConnectionInitiatorController {
    fun onInvitationReceived(connection: PeerConnection, invitation: Invitation): CallbackResult
    fun onRequestSent(connection: PeerConnection, request: ConnectionRequest)
    fun onResponseReceived(connection: PeerConnection, response: ConnectionResponse): CallbackResult
    fun onCompleted(connection: PeerConnection)
    fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?)
    fun onFailure(
        connection: PeerConnection?,
        error: DidExchangeError,
        message: String? = null,
        details: String? = null,
        stackTrace: String? = null
    )

}

package com.dxc.ssi.agent.api.callbacks.didexchange

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection

interface ConnectionInitiatorController {
    fun onInvitationReceived(connection: Connection, endpoint: String, invitation: Invitation): CallbackResult
    fun onRequestSent(connection: Connection, request: ConnectionRequest): CallbackResult
    fun onResponseReceived(connection: Connection, response: ConnectionResponse): CallbackResult
    fun onCompleted(connection: Connection): CallbackResult
    fun onAbandoned(connection: Connection, problemReport: ProblemReport): CallbackResult
}

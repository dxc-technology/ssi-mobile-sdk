package com.dxc.ssi.agent.api.callbacks.didexchange

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.common.ProblemReport
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.SharedConnection

interface ConnectionInitiatorController {
    fun onInvitationReceived(connection: SharedConnection, endpoint: String, invitation: Invitation): CallbackResult
    fun onRequestSent(connection: SharedConnection, request: ConnectionRequest): CallbackResult
    fun onResponseReceived(connection: SharedConnection, response: ConnectionResponse): CallbackResult
    fun onCompleted(connection: SharedConnection): CallbackResult
    fun onAbandoned(connection: SharedConnection, problemReport: ProblemReport): CallbackResult
}

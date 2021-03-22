package com.dxc.ssi.agent.didcomm.actions.trustping

import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.Connection


class ReceiveTrustPingResponseAction(val trustPingTrackerService: TrustPingTrackerService, private val connection: Connection) {
    fun perform(): ActionResult {
        trustPingTrackerService.trustPingResponseReceivedEvent(connection)
        return ActionResult()
    }
}
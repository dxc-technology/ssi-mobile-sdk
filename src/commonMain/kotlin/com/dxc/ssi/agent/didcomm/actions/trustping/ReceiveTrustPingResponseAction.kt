package com.dxc.ssi.agent.didcomm.actions.trustping

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ReceiveTrustPingResponseAction(private val actionParams: ActionParams): Action {
    override suspend fun perform(): ActionResult {
        val messageContext = actionParams.messageContext
        val connection = messageContext.connection!!
        val trustPingTrackerService = actionParams.trustPingTrackerService!!

        val trustPingResponseMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage.message)

        trustPingTrackerService.trustPingResponseReceivedEvent(connection)
        return ActionResult()
    }
}
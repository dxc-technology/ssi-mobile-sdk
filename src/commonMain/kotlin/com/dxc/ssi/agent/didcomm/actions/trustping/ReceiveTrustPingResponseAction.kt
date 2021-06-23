package com.dxc.ssi.agent.didcomm.actions.trustping

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ReceiveTrustPingResponseAction(private val actionParams: ActionParams): Action {
    override suspend fun perform(): ActionResult {
        println("Entered perform fun")
        val messageContext = actionParams.messageContext
        println("Got messageContext")
        val connection = messageContext.connection!!
        println("Got connection")
        val connectionsTrackerService = actionParams.services.connectionsTrackerService!!
        println("Got trustPingService")

        val trustPingResponseMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage.message)

        println("Decoded trustPingResponseMessage")

        connectionsTrackerService.trustPingResponseReceivedEvent(connection)
        println("Marked ping message as received")
        return ActionResult()
    }
}
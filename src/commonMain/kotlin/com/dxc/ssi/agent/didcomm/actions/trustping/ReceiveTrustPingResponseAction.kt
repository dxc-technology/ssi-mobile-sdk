package com.dxc.ssi.agent.didcomm.actions.trustping

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ReceiveTrustPingResponseAction(private val actionParams: ActionParams) : Action {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun perform(): ActionResult {
        println("Entered perform fun")
        val messageContext = actionParams.context
        println("Got messageContext")


        messageContext!!.connection?.let { connection ->
            println("Got connection")
            val connectionsTrackerService = actionParams.services.connectionsTrackerService!!
            println("Got trustPingService")

            val trustPingResponseMessage =
                json.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage!!.message)

            println("Decoded trustPingResponseMessage")

            connectionsTrackerService.trustPingResponseReceivedEvent(connection)
            actionParams.callbacks.trustPingController?.onTrustPingResponseReceived(connection)
            println("Marked ping message as received")

        }


        return ActionResult()
    }
}
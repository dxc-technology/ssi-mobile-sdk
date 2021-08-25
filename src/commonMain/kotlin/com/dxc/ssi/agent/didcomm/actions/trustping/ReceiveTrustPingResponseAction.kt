package com.dxc.ssi.agent.didcomm.actions.trustping

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ReceiveTrustPingResponseAction(private val actionParams: ActionParams) : Action {
    private val logger: Kermit = Kermit(LogcatLogger())
    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun perform(): ActionResult {
        logger.d { "Entered perform fun" }
        val messageContext = actionParams.context
        logger.d { "Got messageContext" }


        messageContext!!.connection?.let { connection ->
            logger.d { "Got connection" }
            val connectionsTrackerService = actionParams.services.connectionsTrackerService!!
            logger.d { "Got trustPingService" }

            val trustPingResponseMessage =
                json.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage!!.message)

            logger.d { "Decoded trustPingResponseMessage" }

            connectionsTrackerService.trustPingResponseReceivedEvent(connection)
            actionParams.callbacks.trustPingController?.onTrustPingResponseReceived(connection)
            logger.d { "Marked ping message as received" }

        }


        return ActionResult()
    }
}
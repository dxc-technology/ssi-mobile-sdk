package com.dxc.ssi.agent.didcomm.actions.trustping

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ReceiveTrustPingResponseAction(private val actionParams: ActionParams): Action {
    private val logger: Kermit = Kermit(LogcatLogger())
    override suspend fun perform(): ActionResult {
        logger.log(Severity.Debug,"",null) { "Entered perform fun" }
        val messageContext = actionParams.context
        logger.log(Severity.Debug,"",null) { "Got messageContext" }
        val connection = messageContext!!.connection!!
        logger.log(Severity.Debug,"",null) { "Got connection" }
        val connectionsTrackerService = actionParams.services.connectionsTrackerService!!
        logger.log(Severity.Debug,"",null) { "Got trustPingService" }
        val trustPingResponseMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage!!.message)

        logger.log(Severity.Debug,"",null) { "Decoded trustPingResponseMessage" }

        connectionsTrackerService.trustPingResponseReceivedEvent(connection)
        actionParams.callbacks.trustPingController?.onTrustPingResponseReceived(connection)
        logger.log(Severity.Debug,"",null) { "Marked ping message as received" }
        return ActionResult()
    }
}
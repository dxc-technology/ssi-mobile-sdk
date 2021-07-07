package com.dxc.ssi.agent.didcomm.actions.trustping

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingRequest
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.dxc.utils.Result

class SendTrustPingAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    val services: Services,
    val processors: Processors,
    val callbacks: Callbacks,
    private val connection: PeerConnection
) {

    suspend fun perform(): ActionResult {
        //TODO: make TrustPing Stateless

        // 1. Form TrustPingRequestMessage

        val requestId = uuid4().toString()

        val trustPingRequest =
            TrustPingRequest(
                id = requestId,
                responseRequested = true
            )


        val result =  MessageSender.packAndSendMessage(
            Message(Json.encodeToString(trustPingRequest)),
            connection,
            walletConnector,
            transport,
            services,
            onMessageSent = {
                services.connectionsTrackerService!!.trustPingSentOverConnectionEvent(connection)
                callbacks.trustPingController?.onTrustPingSent(connection)
                //TODO: instead of true, set some other status, like trustPing is sent, and we do not know the result yet
                Result.Success(ActionResult(trustPingSuccessful = true))

            },
            onMessageSendingFailure = {
                val problemReport = ProblemReport(
                    id = uuid4().toString(),
                    description = DidCommProblemCodes.COULD_NOT_DELIVER_MESSAGE.toProblemReportDescription()
                )
                Result.Success(ActionResult(trustPingSuccessful = false))
            }
        )

        return (result as Result.Success).data as ActionResult

    }
}
package com.dxc.ssi.agent.didcomm.actions.abandon.impl

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.abandon.AbandonAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.model.abandon.AbandonConnectionAnnounce
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.services.ConnectionsTrackerService
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.model.messages.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//TODO: think about using generic action params here
//TODO: think if we need to unify it with AbortConnection
class InitiateAbandonConnectionAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    val services: Services,
    val callbacks: Callbacks,
    val connection: PeerConnection,
    val notifyPeerBeforeAbandoning: Boolean,
    val problemReport: ProblemReport?
) : AbandonAction {
    private val logger: Kermit = Kermit(LogcatLogger())

    override suspend fun perform(): ActionResult {

        //1.  Check if connection is opened and active?
        val storedConnection = walletConnector.walletHolder.getConnectionRecordById(connection.id)



        when {
            storedConnection?.state == PeerConnectionState.COMPLETED -> {
                if (notifyPeerBeforeAbandoning) {
                    val abandonConnectionAnnounce = AbandonConnectionAnnounce(id = uuid4().toString())
                    val abandonConnectionAnnounceJson = Json.encodeToString(abandonConnectionAnnounce)

                    MessageSender.packAndSendMessage(
                        Message(abandonConnectionAnnounceJson),
                        connection,
                        walletConnector,
                        transport,
                        services,
                        onMessageSendingFailure = {
                            logger.log(Severity.Debug,"",null) { "Warning: we could not notify remote peer that we are abandoning connection. Abandoning without notification" }
                            null
                        }
                    )
                }

                transport.disconnect(connection)
                abandonConnectionOnOurSide()

            }
            storedConnection != null -> {
                //TODO: currently we won't handle separately case when connection is in process of establishment
                transport.disconnect(connection)
                abandonConnectionOnOurSide()
            }
            else -> {
                // Looks like there is no such record, but just in case, try to disconnect transport
                transport.disconnect(connection)
            }
        }
        return ActionResult()
    }

    private suspend fun abandonConnectionOnOurSide() {
        val updatedConnection = connection.copy(state = PeerConnectionState.ABANDONED)
        walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
        callbacks.connectionInitiatorController!!.onAbandoned(updatedConnection, problemReport)

    }
}

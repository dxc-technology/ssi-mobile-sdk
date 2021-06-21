package com.dxc.ssi.agent.didcomm.actions.abandon.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.abandon.AbandonAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.model.abandon.AbandonConnectionAnnounce
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
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
    trustPingTrackerService: TrustPingTrackerService,
    val connection: PeerConnection,
    val notifyPeerBeforeAbandoning: Boolean
) : AbandonAction {
    override suspend fun perform(): ActionResult {

        //1.  Check if connection is opened and active?
        val storedConnection = walletConnector.walletHolder.getConnectionRecordById(connection.id)


        when {
            storedConnection?.state == PeerConnectionState.COMPLETED -> {
                if (notifyPeerBeforeAbandoning) {
                    val abandonConnectionAnnounce = AbandonConnectionAnnounce(id = uuid4().toString())
                    val abandonConnectionAnnounceJson = Json.encodeToString(abandonConnectionAnnounce)
                    try {
                        MessageSender.packAndSendMessage(
                            Message(abandonConnectionAnnounceJson),
                            connection,
                            walletConnector,
                            transport
                        )
                    } catch (t: Throwable) {
                        // If we were not able to send this message then simply ignore it and continue to connection closure
                    }
                }

                transport.disconnect(connection)
                walletConnector.walletHolder.storeConnectionRecord(connection.copy(state = PeerConnectionState.ABANDONED))

            }
            storedConnection != null -> {
                //TODO: currently we won't handle separately case when connection is in process of establishment
                transport.disconnect(connection)
                walletConnector.walletHolder.storeConnectionRecord(connection.copy(state = PeerConnectionState.ABANDONED))
            }
            else -> {
                // Looks like there is no such record, but just in case, try to disconnect transport
                transport.disconnect(connection)
            }
        }
        return ActionResult()
    }
}

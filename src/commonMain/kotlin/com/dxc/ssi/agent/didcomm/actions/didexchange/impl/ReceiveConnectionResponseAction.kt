package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.model.PeerConnectionState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


//TODO: Think about more generic actions constructor parameters and returns
class ReceiveConnectionResponseAction(
    private val actionParams: ActionParams
) : DidExchangeAction {
    private val logger: Kermit = Kermit(LogcatLogger())
    override suspend fun perform(): ActionResult {

        val connectionInitiatorController = actionParams.callbacks.connectionInitiatorController!!
        val messageContext = actionParams.context
        val walletConnector = actionParams.walletConnector
        val trustPingProcessor = actionParams.processors.trustPingProcessor!!


        val connectionResponse =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<ConnectionResponse>(messageContext!!.receivedUnpackedMessage!!.message)


        val ourConnectionId = connectionResponse.thread.thid
        val ourConnection = walletConnector.walletHolder.getConnectionRecordById(ourConnectionId)

        logger.log(Severity.Debug,"",null) { "Existing connection record $ourConnection" }

        //TODO: move the decision below to some abstraction layer? StateMahine?
        when (ourConnection!!.state) {
            //TODO: Create some enum for possible states
            PeerConnectionState.REQUEST_SENT -> {
                connectionInitiatorController.onResponseReceived(ourConnection, connectionResponse)
                //TODO: think how to avoid NPE here
                val updatedConnection = ourConnection.copy(
                    state = PeerConnectionState.COMPLETED,
                    peerVerkey = messageContext.receivedUnpackedMessage!!.senderVerKey
                )
                walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
                trustPingProcessor.sendTrustPingOverConnection(updatedConnection)
                connectionInitiatorController.onCompleted(updatedConnection)
                logger.log(Severity.Debug,"",null) { "ReceiveConnectionResponseAction: after completed callback" }

                return ActionResult(updatedConnection)
            }

            else -> TODO("Not implemented") // process different possibilities here according to state diagram

        }


    }

}
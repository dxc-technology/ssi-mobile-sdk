package com.dxc.ssi.agent.didcomm.actions.didexchange.impl

import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.didexchange.DidExchangeAction
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


//TODO: Think about more generic actions constructor parameters and returns
class ReceiveConnectionResponseAction(
    private val actionParams: ActionParams
) : DidExchangeAction {
    override fun perform(): ActionResult {

        val connectionInitiatorController = actionParams.callbacks.connectionInitiatorController!!
        val messageContext = actionParams.messageContext
        val walletConnector = actionParams.walletConnector
        val trustPingProcessor = actionParams.trustPingProcessor!!


        val connectionResponse =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<ConnectionResponse>(messageContext.receivedUnpackedMessage.message)


        val ourConnectionId = connectionResponse.thread.thid
        val ourConnection = walletConnector.walletHolder.getConnectionRecordById(ourConnectionId)

        println("Existing connection record $ourConnection")

        //TODO: move the decision below to some abstraction layer? StateMahine?
        when (ourConnection!!.state) {
            //TODO: Create some enum for possible states
            "RequestSent" -> {
                connectionInitiatorController.onResponseReceived(ourConnection, connectionResponse)
                //TODO: think how to avoid NPE here
                val updatedConnection = ourConnection.copy(
                    state = "Complete",
                    peerVerkey = messageContext.receivedUnpackedMessage.senderVerKey
                )
                walletConnector.walletHolder.storeConnectionRecord(updatedConnection)
                connectionInitiatorController.onCompleted(ourConnection)
                trustPingProcessor.sendTrustPingOverConnection(ourConnection)
                return ActionResult(updatedConnection)
            }

            else -> TODO("Not implemented") // process different possibilities here according to state diagram

        }


    }

}
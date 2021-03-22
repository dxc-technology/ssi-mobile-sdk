package com.dxc.ssi.agent.didcomm.processor.trustping

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.trustping.ReceiveTrustPingResponseAction
import com.dxc.ssi.agent.didcomm.actions.trustping.SendTrustPingAction
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.MessageContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor
//TODO: remove all redundant part of code left from DidExchangeProcessor
class TrustPingProcessorImpl(
    private val walletConnector: WalletConnector,
    private val transport: Transport,
    private val trustPingTrackerService: TrustPingTrackerService
    //TODO: introduce callbacks for TrustPing
) : TrustPingProcessor {

    override fun sendTrustPingOverConnection(connection: Connection): Boolean {
        val sendTrustPingAction = SendTrustPingAction(walletConnector, transport, trustPingTrackerService, connection)
        val actionResult = sendTrustPingAction.perform()

        return actionResult.trustPingSuccessful!!
    }

    //TODO: see if it is possible to generalize this function in order to go back to AbstractProcessor
    override fun processMessage(messageContext: MessageContext) {
        when (getMessageType(messageContext.receivedUnpackedMessage.message)) {
            TrustPingMessageType.TRUST_PING -> {
                //TODO: check incoming TrustPings and reply to them properly
            }
            TrustPingMessageType.TRUST_PING_RESPONSE -> {
                val trustPingResponseMessage =
                    Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString<TrustPingResponse>(messageContext.receivedUnpackedMessage.message)

                //TODO:
                //   Potentially need check somewhere that we received response on our particular message
                //   if (trustPingResponse.thread.thid == requestId)
                //            return ActionResult(trustPingSuccessful = true)
                ReceiveTrustPingResponseAction(trustPingTrackerService, messageContext.connection!!).perform()
            }
        }
    }

    enum class TrustPingMessageType {
        //TODO:  add ProblemReport/error message
        TRUST_PING,
        TRUST_PING_RESPONSE
    }

    private fun getMessageType(message: String): TrustPingMessageType {

        val typeAttribute =
            Json { ignoreUnknownKeys = true }.decodeFromString<BasicMessageWithTypeOnly>(message).type

        //TODO: change to regexp to allow more strict matching
        val messageType = when {
            typeAttribute.contains("trust_ping/1.0/ping_response") -> TrustPingMessageType.TRUST_PING_RESPONSE
            typeAttribute.contains("trust_ping/1.0/ping") -> TrustPingMessageType.TRUST_PING
            else -> throw IllegalArgumentException("Unknown message type: $typeAttribute")
        }

        println("Determined message type: $messageType")

        return messageType
    }
}
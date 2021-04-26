package com.dxc.ssi.agent.didcomm.actions.trustping

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingRequest
import com.dxc.ssi.agent.didcomm.model.trustping.TrustPingResponse
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SendTrustPingAction(
    val walletConnector: WalletConnector,
    val transport: Transport,
    private val connection: Connection
) {

    suspend fun perform(): ActionResult {
        //TODO: make TrustPing Stateless

        // 1. Form TrustPingRequestMessage

        val requestId = uuid4().toString()

        val trustPingRequest =
            TrustPingRequest(
                type = "https://didcomm.org/trust_ping/1.0/ping",
                id = requestId,
                responseRequested = true
            )

        val trustPingRequestJson = Json.encodeToString(trustPingRequest)

        val messageEnvelop =
            MessageEnvelop(
                payload = walletConnector.walletHolder.packMessage(
                    Message(trustPingRequestJson),
                    connection.peerRecipientKeys
                )
            )

        println("Packed message: ${messageEnvelop.payload}")

        // 2. Send TrustPingRequestMessage

        //TODO: ensure that transport function is synchronous here because we will save new status to wallet only after actual message was sent
        transport.sendMessage(
            connection,
            messageEnvelop
        )

        // 3. Block and wait for trust ping result for specific timeout
        //TODO: this is not scalable and not allowing parallel connections, only for POC purpose. Needs to be reworked
        //Next message can be any message, not necessarily TrustPingResponse
        //Idiomatic way would probably not to call transport directly, but go through general flow of messageListener
        /*
        * Idiomatic way would be following
        *
        * SubscribeOn(Trust Ping Message Received) With Timeout
        * if recieved
        *   return true
        * if timed out
        *   return false
        *
        * */

        val receivedMessage = transport.receiveNextMessage()

        val unpackedMessage = walletConnector.walletHolder.unPackMessage(Message(receivedMessage.payload))

        val receivedUnpackedMessage = Json.decodeFromString<ReceivedUnpackedMessage>(unpackedMessage.payload)

        val trustPingResponse =
            Json { ignoreUnknownKeys = true }.decodeFromString<TrustPingResponse>(receivedUnpackedMessage.message)

        if(trustPingResponse.thread.thid == requestId)
            return ActionResult(trustPingSuccessful = true)

        return ActionResult(trustPingSuccessful = false)

    }
}
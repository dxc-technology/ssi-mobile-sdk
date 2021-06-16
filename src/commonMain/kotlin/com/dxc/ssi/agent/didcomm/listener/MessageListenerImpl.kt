package com.dxc.ssi.agent.didcomm.listener

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.router.MessageRouter
import com.dxc.ssi.agent.didcomm.router.MessageRouterImpl
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageContext
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MessageListenerImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val trustPingTrackerService: TrustPingTrackerService,
    callbacks: Callbacks
) :
    MessageListener {

    private var isShutdown: Boolean = false
    override val messageRouter: MessageRouter =
        MessageRouterImpl(walletConnector, ledgerConnector, trustPingTrackerService, transport, callbacks)

    override fun shutdown() {
        isShutdown = true
    }

    override suspend fun listen() {

        println("Started listener")

        while (!isShutdown) {

            println("Message Listener: Checking for new messages")

            val receivedMessage = transport.receiveNextMessage()

            println("Message Listener: Received message")

            val messageContext = unpackAndBuildMesageContext(receivedMessage)


            messageRouter.routeAndProcessMessage(messageContext)
            println("Message Listener: : procesed message")
        }

    }


    suspend fun unpackAndBuildMesageContext(receivedMessage: MessageEnvelop): MessageContext {

        val unpackedMessage = walletConnector.walletHolder.unPackMessage(Message(receivedMessage.payload))
        val receivedUnpackedMessage = Json.decodeFromString<ReceivedUnpackedMessage>(unpackedMessage.payload)
        println("Received Unpacked message: $receivedUnpackedMessage")

        println(
            "sender verkey = ${receivedUnpackedMessage.senderVerKey}" +
                    "receiver_verkey = ${receivedUnpackedMessage.recipientVerKey}"
        )
        val connection = getConnectionByVerkey(receivedUnpackedMessage.senderVerKey)

        return MessageContext(connection, receivedUnpackedMessage)

    }

    private suspend fun getConnectionByVerkey(senderVerKey: String): SharedConnection? {
        return walletConnector.walletHolder.findConnectionByVerKey(senderVerKey)
    }

}

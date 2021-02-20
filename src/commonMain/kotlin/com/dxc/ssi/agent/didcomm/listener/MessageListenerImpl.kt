package com.dxc.ssi.agent.didcomm.listener

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.model.didexchange.DidDocument
import com.dxc.ssi.agent.didcomm.router.MessageRouter
import com.dxc.ssi.agent.didcomm.router.MessageRouterImpl
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MessageListenerImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    callbacks: Callbacks
) :
    MessageListener {

    private var isShutdown: Boolean = false
    override val messageRouter: MessageRouter = MessageRouterImpl(walletConnector, transport, callbacks)

    override fun shutdown() {
        isShutdown = true
    }

    override fun listen() {

        println("Started listener")

        while (!isShutdown) {

            println("Checking for new messages")

            val receivedMessage = transport.receiveNextMessage()

            val unpackedMessage = walletConnector.walletHolder.unPackMessage(Message(receivedMessage.payload))

            val receivedUnpackedMessage = Json.decodeFromString<ReceivedUnpackedMessage>(unpackedMessage.payload)

            println("Received Unpacked message: $receivedUnpackedMessage")

            messageRouter.routeAndProcessMessage(receivedUnpackedMessage)
            println("procesed message")
        }

    }

}

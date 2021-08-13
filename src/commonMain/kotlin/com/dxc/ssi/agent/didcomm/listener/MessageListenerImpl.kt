package com.dxc.ssi.agent.didcomm.listener

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.router.MessageRouter
import com.dxc.ssi.agent.didcomm.router.MessageRouterImpl
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.Context
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MessageListenerImpl(
    private val transport: Transport,
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val services: Services,
    callbacks: Callbacks
) :
    MessageListener {

    private val logger: Kermit = Kermit(LogcatLogger())
    private var isShutdown: Boolean = false
    override val messageRouter: MessageRouter =
        MessageRouterImpl(walletConnector, ledgerConnector, services, transport, callbacks)

    override fun shutdown() {
        isShutdown = true
    }

    override suspend fun listen() {

        logger.d { "Started listener" }
        while (!isShutdown) {

            logger.d { "Message Listener: Checking for new messages" }

            val receivedMessage = transport.receiveNextMessage()

            logger.d { "Message Listener: Received message" }

            val messageContext = unpackAndBuildMesageContext(receivedMessage)


            messageRouter.routeAndProcessMessage(messageContext)
            logger.d { "Message Listener: : procesed message" }

        }

    }


    suspend fun unpackAndBuildMesageContext(receivedMessage: MessageEnvelop): Context {

        val unpackedMessage = walletConnector.walletHolder.unPackMessage(Message(receivedMessage.payload))
        val receivedUnpackedMessage = Json.decodeFromString<ReceivedUnpackedMessage>(unpackedMessage.payload)

        logger.d { "Received Unpacked message: $receivedUnpackedMessage" }

        logger.d {  "sender verkey = ${receivedUnpackedMessage.senderVerKey}" +
                "receiver_verkey = ${receivedUnpackedMessage.recipientVerKey}" }

        val connection = getConnectionByVerkey(receivedUnpackedMessage.senderVerKey)

        return Context(connection, receivedUnpackedMessage)

    }

    private suspend fun getConnectionByVerkey(senderVerKey: String): PeerConnection? {
        return walletConnector.walletHolder.findConnectionByVerKey(senderVerKey)
    }

}

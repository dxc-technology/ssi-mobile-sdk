package com.dxc.ssi.agent.didcomm.commoon

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.exceptions.transport.MessageCouldNotBeDeliveredException
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.ConnectionTransportState
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.utils.Result

//TODO: understand what should be responsibility of this helper and how we can avoid it
object MessageSender {

    private val logger: Kermit = Kermit(LogcatLogger())
    suspend fun packAndSendMessage(
        message: Message,
        connection: PeerConnection,
        walletConnector: WalletConnector,
        transport: Transport,
        services: Services,
        onMessageSendingFailure: (suspend () -> Result<Any>?)? = null, //TODO: make this optional
        onMessageSent: (suspend () -> Result<Any>?)? = null
    ): Result<Any>? {

        logger.d { "MessageSender: preparing to pack and send message: $message" }
        val messageToSend = MessagePacker.packAndPrepareForwardMessage(message, connection, walletConnector)

        return try {
            transport.sendMessage(connection, messageToSend)
            services.connectionsTrackerService!!.setConnectionTransportState(connection, ConnectionTransportState.CONNECTED)
            logger.d { "MessageSender: sent message: $message" }
            onMessageSent?.invoke()
        } catch (e: MessageCouldNotBeDeliveredException) {
            services.connectionsTrackerService!!.setConnectionTransportState(connection, ConnectionTransportState.DISCONNECTED)
            onMessageSendingFailure?.invoke()
        }


    }

}
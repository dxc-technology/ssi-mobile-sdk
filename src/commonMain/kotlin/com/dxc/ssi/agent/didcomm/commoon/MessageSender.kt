package com.dxc.ssi.agent.didcomm.commoon

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Message
//TODO: understand what should be responsibility of this helper and how we can avoid it
object MessageSender {

    suspend fun packAndSendMessage(
        message: Message,
        connection: PeerConnection,
        walletConnector: WalletConnector,
        transport: Transport
    ) {

        println("MessageSender: preparing to pack and send message: $message")
        val messageToSend = MessagePacker.packAndPrepareForwardMessage(message, connection, walletConnector)

        //TODO: ensure that transport function is synchronous here because we will save new status to wallet only after actual message was sent
        transport.sendMessage(connection, messageToSend)

        println("MessageSender: sent message: $message")

    }

}
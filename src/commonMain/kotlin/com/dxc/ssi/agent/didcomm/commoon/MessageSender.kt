package com.dxc.ssi.agent.didcomm.commoon

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.ssi.agent.model.messages.Message
//TODO: understand what should be responsibility of this helper and how we can avoid it
object MessageSender {

    suspend fun packAndSendMessage(
        message: Message,
        connection: SharedConnection,
        walletConnector: WalletConnector,
        transport: Transport
    ) {

        val messageToSend = MessagePacker.packAndPrepareForwardMessage(message, connection, walletConnector)

        //TODO: ensure that transport function is synchronous here because we will save new status to wallet only after actual message was sent
        transport.sendMessage(connection, messageToSend)

    }

}
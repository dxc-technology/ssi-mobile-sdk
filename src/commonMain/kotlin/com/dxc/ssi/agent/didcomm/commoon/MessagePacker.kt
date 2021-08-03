package com.dxc.ssi.agent.didcomm.commoon

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.forward.BuildForwardMessage
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MessagePacker {
    private val logger: Kermit = Kermit(LogcatLogger())
    //TODO: understand what Message models we need and unify them
    suspend fun packAndPrepareForwardMessage(
        message: Message,
        connection: PeerConnection,
        walletConnector: WalletConnector
    ): MessageEnvelop {

        //TODO: check if we always need to pack our message into forward message
        val messageEnvelop = MessageEnvelop(
            payload = walletConnector.walletHolder.packMessage(
                message,
                connection.peerRecipientKeys
            )
        )

        logger.log(Severity.Debug,"",null) { "Packed message: ${messageEnvelop.payload}" }

        //TODO: understand how to handle case with multiple recepient keys. Should we form forward message for each of those keys?
        val forwardMessage = BuildForwardMessage.buildForwardMessage(messageEnvelop, connection.peerRecipientKeys.first())

        val outerMessageEnvelop = MessageEnvelop(
            payload = walletConnector.walletHolder.packMessage(
                Message(Json.encodeToString(forwardMessage)),
                connection.peerRecipientKeys,
                useAnonCrypt = true
            )
        )

        logger.log(Severity.Debug,"",null) { "Packed forward message: ${outerMessageEnvelop.payload}" }

        return  outerMessageEnvelop

    }
}
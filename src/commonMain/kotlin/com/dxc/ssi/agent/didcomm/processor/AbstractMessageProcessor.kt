package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.Processors
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.processor.abandon.AbandonConnectionProcessor
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.didcomm.services.TrustPingTrackerService
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.MessageContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class AbstractMessageProcessor(
    //TODO: bundle properties into bundles : i.e. pluggable, callbacks, services, processors and potentially bundle them all together into smth like "context"
    val walletConnector: WalletConnector,
    val ledgerConnector: LedgerConnector,
    val transport: Transport,
    val callbacks: Callbacks,
    val processors: Processors,
    val trustPingTrackerService: TrustPingTrackerService?,
) : MessageProcessor {


    override suspend fun processMessage(messageContext: MessageContext) {
        println("Started processing message $messageContext")

        val actionParams = ActionParams(
            walletConnector = walletConnector,
            ledgerConnector = ledgerConnector,
            transport = transport,
            callbacks = callbacks,
            messageContext = messageContext,
            processors = processors,
            trustPingTrackerService = trustPingTrackerService
        )

        val actionResult = getMessageType(messageContext.receivedUnpackedMessage.message)
            .getMessageHandler()
            .invoke(actionParams)
            .perform()

        println("action completed with result : $actionResult")

    }

    abstract fun getMessageType(message: String): MessageType


    inline fun <reified T : Enum<T>> getMessageTypeGeneric(message: String): T {
        val typeAttribute =
            Json { ignoreUnknownKeys = true }.decodeFromString<BasicMessageWithTypeOnly>(message).type
        val messageType = enumValues<T>().single { Regex((it as MessageType).getTypeString()).matches(typeAttribute) }
        println("Determined message type: $messageType")
        return messageType
    }

}
package com.dxc.ssi.agent.didcomm.processor

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class AbstractMessageProcessor(
    //TODO: bundle properties into bundles : i.e. pluggable, callbacks, services, processors and potentially bundle them all together into smth like "context"
    val walletConnector: WalletConnector,
    val ledgerConnector: LedgerConnector,
    val transport: Transport,
    val callbacks: Callbacks,
    val processors: Processors,
    val services: Services,
) : MessageProcessor {
    var logger: Kermit = Kermit(LogcatLogger())

    override suspend fun processMessage(context: Context) {

        logger.log(Severity.Debug,"",null) { "Started processing message $context" }

        val actionParams = ActionParams(
            walletConnector = walletConnector,
            ledgerConnector = ledgerConnector,
            transport = transport,
            callbacks = callbacks,
            context = context,
            processors = processors,
            services = services
        )

        val actionResult = getMessageType(context.receivedUnpackedMessage!!.message)
            .getMessageHandler()
            .invoke(actionParams)
            .perform()

        logger.log(Severity.Debug,"",null) { "action completed with result : $actionResult" }

    }

    abstract fun getMessageType(message: String): MessageType


    inline fun <reified T : Enum<T>> getMessageTypeGeneric(message: String): T {
        val typeAttribute =
            Json { ignoreUnknownKeys = true }.decodeFromString<BasicMessageWithTypeOnly>(message).type
        val messageType = enumValues<T>().single { Regex((it as MessageType).getTypeString()).matches(typeAttribute) }
        logger.log(Severity.Debug,"",null) { "Determined message type: $messageType" }
        return messageType
    }

}
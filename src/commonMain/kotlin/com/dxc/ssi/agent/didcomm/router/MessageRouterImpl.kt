package com.dxc.ssi.agent.didcomm.router

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.didcomm.processor.abandon.AbandonConnectionProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.issue.CredIssuerProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.verify.CredVerifierProcessorImpl
import com.dxc.ssi.agent.didcomm.services.Services
import com.dxc.ssi.agent.model.messages.BasicMessageWithTypeOnly
import com.dxc.ssi.agent.model.messages.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MessageRouterImpl(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val services: Services,
    private val transport: Transport,
    private val callbacks: Callbacks
) :
    MessageRouter {

    override val processors = Processors()

    init {

        processors.trustPingProcessor = TrustPingProcessorImpl(
            walletConnector,
            ledgerConnector,
            transport,
            callbacks,
            processors,
            services
        )

        processors.abandonConnectionProcessor = AbandonConnectionProcessorImpl(
            walletConnector,
            ledgerConnector,
            transport,
            callbacks,
            processors,
            services
        )

        processors.credIssuerProcessor = CredIssuerProcessorImpl(
            walletConnector,
            ledgerConnector,
            transport,
            callbacks,
            processors,
            services,
        )

        processors.didExchangeProcessor = DidExchangeProcessorImpl(
            walletConnector,
            ledgerConnector,
            transport,
            callbacks,
            processors,
            services
        )


        processors.credVerifierProcessor = CredVerifierProcessorImpl(
            walletConnector,
            ledgerConnector,
            transport,
            callbacks,
            processors,
            services
        )


    }

    //TODO: check connection in message context and throw exception if it is not null if it is expected to be non-null
    override suspend fun routeAndProcessMessage(context: Context) {

        when (determineRoute(context)) {
            //TODO: add route for forward message
            Route.DidExchange -> processors.didExchangeProcessor!!.processMessage(context)
            Route.CredIssuer -> processors.credIssuerProcessor!!.processMessage(context)
            Route.CredVerifier -> processors.credVerifierProcessor!!.processMessage(context)
            Route.TrustPing -> processors.trustPingProcessor!!.processMessage(context)
            Route.AbandonConnection -> processors.abandonConnectionProcessor!!.processMessage(context)

        }
    }

    fun determineRoute(context: Context): Route {
        //TODO: unify this extraction of type from message. Currently it is used in sevral places
        val typeAttribute =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<BasicMessageWithTypeOnly>(context.receivedUnpackedMessage!!.message).type

        val route = when {
            typeAttribute.contains("/issue-credential/1") -> Route.CredIssuer
            typeAttribute.contains("/connections/1") -> Route.DidExchange
            typeAttribute.contains("/present-proof/1") -> Route.CredVerifier
            typeAttribute.contains("/trust_ping/1") -> Route.TrustPing
            typeAttribute.contains("/abandon_connection/1.0") -> Route.AbandonConnection
            else -> throw IllegalArgumentException("Unknown message type: $typeAttribute")
        }

        println("Determined route: $route")
        return route

    }

    //TODO: do we need separate root for forward message? is it a protocol or just an envelop?
    enum class Route {
        DidExchange,
        CredIssuer,
        CredVerifier,
        TrustPing,
        AbandonConnection
    }
}

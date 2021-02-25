package com.dxc.ssi.agent.didcomm.router

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessor
import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.issue.CredIssuerProcessor
import com.dxc.ssi.agent.didcomm.processor.issue.CredIssuerProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessorImpl
import com.dxc.ssi.agent.didcomm.processor.verify.CredVerifierProcessor
import com.dxc.ssi.agent.didcomm.processor.verify.CredVerifierProcessorImpl
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage

class MessageRouterImpl(
    private val walletConnector: WalletConnector,
    private val transport: Transport,
    private val callbacks: Callbacks
) :
    MessageRouter {

    override val trustPingProcessor = TrustPingProcessorImpl(walletConnector, transport)
    override val didExchangeProcessor: DidExchangeProcessor = DidExchangeProcessorImpl(
        walletConnector,
        transport,
        trustPingProcessor,
        callbacks.connectionInitiatorController,
        callbacks.connectionResponderController
    )
    private val credIssuerProcessor: CredIssuerProcessor = CredIssuerProcessorImpl(walletConnector, transport)
    private val credVerifierProcessor: CredVerifierProcessor = CredVerifierProcessorImpl(walletConnector, transport)

    override fun routeAndProcessMessage(receivedUnpackedMessage: ReceivedUnpackedMessage) {

        val message = Message(receivedUnpackedMessage.message)

        val route = determineRoute(message)

        //TODO: think about concurrency here
        when (route) {
            //TODO: add route for forward message
            Route.DidExchange -> didExchangeProcessor.processMessage(message)
            Route.CredIssuer -> credIssuerProcessor.processMessage(message)
            Route.CredVerifier -> credVerifierProcessor.processMessage(message)
        }

    }

    fun determineRoute(message: Message): Route {
        //TODO: implement proper routing here
        return Route.DidExchange

    }


    enum class Route {
        DidExchange,
        CredIssuer,
        CredVerifier
    }


}

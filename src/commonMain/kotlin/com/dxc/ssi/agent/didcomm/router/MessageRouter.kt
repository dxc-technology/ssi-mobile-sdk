package com.dxc.ssi.agent.didcomm.router

import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessor
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageContext
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage

interface MessageRouter {
    val didExchangeProcessor: DidExchangeProcessor
    val trustPingProcessor: TrustPingProcessor
    suspend fun routeAndProcessMessage(messageContext: MessageContext)

}

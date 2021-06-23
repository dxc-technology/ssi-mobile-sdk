package com.dxc.ssi.agent.didcomm.router

import com.dxc.ssi.agent.didcomm.Processors
import com.dxc.ssi.agent.didcomm.processor.abandon.AbandonConnectionProcessor
import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessor
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageContext
import com.dxc.ssi.agent.model.messages.ReceivedUnpackedMessage

interface MessageRouter {
    val processors:Processors
    suspend fun routeAndProcessMessage(messageContext: MessageContext)

}

package com.dxc.ssi.agent.didcomm.processor.trustping

import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageContext

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor

interface TrustPingProcessor {
    //TODO: think about special data structure Invitation instead of String
    suspend fun sendTrustPingOverConnection(connection: Connection): Boolean
    fun processMessage(messageContext: MessageContext)

}

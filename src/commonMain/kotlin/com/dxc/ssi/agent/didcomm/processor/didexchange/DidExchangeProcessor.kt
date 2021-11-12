package com.dxc.ssi.agent.didcomm.processor.didexchange

import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Context

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor

interface DidExchangeProcessor {
    //TODO: think about special data structure Invitation instead of String
    suspend fun initiateConnectionByInvitation(
        invitation: String,
        keepConnectionAlive: Boolean,
        ip: String,
        port: Int
    ): PeerConnection?
    suspend fun processMessage(context: Context)


}

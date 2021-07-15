package com.dxc.ssi.agent.didcomm.processor.abandon

import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.messages.Context

//TODO: for now this class won;t be part of abstraction, once it is implemented see if it is posible to generalize it with MessageProcessor and AbstractMessageProcessor

interface AbandonConnectionProcessor {
    suspend fun processMessage(context: Context)
    suspend fun abandonConnection(connection: PeerConnection, notifyPeerBeforeAbandoning: Boolean, problemReport: ProblemReport? = null)

}

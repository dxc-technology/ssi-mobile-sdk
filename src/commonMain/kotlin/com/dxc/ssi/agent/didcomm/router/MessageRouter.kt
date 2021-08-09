package com.dxc.ssi.agent.didcomm.router

import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.model.messages.Context

interface MessageRouter {
    val processors: Processors
    suspend fun routeAndProcessMessage(context: Context)

}

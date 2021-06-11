package com.dxc.ssi.agent.didcomm.listener

import com.dxc.ssi.agent.didcomm.router.MessageRouter

interface MessageListener {
    val messageRouter: MessageRouter
    fun shutdown()
    suspend fun listen()

}

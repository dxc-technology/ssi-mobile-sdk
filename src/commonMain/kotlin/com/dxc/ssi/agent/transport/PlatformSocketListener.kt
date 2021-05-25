package com.dxc.ssi.agent.transport

interface PlatformSocketListener {
    fun onOpen()
    fun onFailure(t: Throwable)
    suspend fun onMessage(msg: String)
    fun onClosing(code: Int, reason: String)
    fun onClosed(code: Int, reason: String)
}
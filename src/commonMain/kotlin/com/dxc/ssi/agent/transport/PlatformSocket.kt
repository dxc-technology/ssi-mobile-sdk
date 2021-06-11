package com.dxc.ssi.agent.transport

internal expect class PlatformSocket(
    url: String
) {
    fun openSocket(socketListenerAdapter: SocketListenerAdapter)
    fun closeSocket(code: Int, reason: String)
    fun sendMessage(msg: String)
}
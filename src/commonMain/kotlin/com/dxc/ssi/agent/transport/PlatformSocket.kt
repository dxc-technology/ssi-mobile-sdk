package com.dxc.ssi.agent.transport

import kotlinx.coroutines.channels.Channel

internal expect class PlatformSocket(
    url: String
) {
    fun openSocket( socketListenerLoosingAdapter: SocketListenerLoosingAdapter)
    fun closeSocket(code: Int, reason: String)
    fun sendMessage(msg: String)
}
package com.dxc.ssi.agent.transport

internal expect class PlatformSocket(
    url: String,
    ip: String,
    port: Int
) {
    fun openSocket(platformSocketListener: PlatformSocketListener)
    fun closeSocket(code: Int, reason: String)
    fun sendMessage(msg: String)
}
package com.dxc.ssi.agent.transport


internal actual class PlatformSocket actual constructor(url: String, ip: String, port: Int) {
    actual fun openSocket(platformSocketListener: PlatformSocketListener) {
    }

    actual fun closeSocket(code: Int, reason: String) {
    }

    actual fun sendMessage(msg: String) {
    }

}
package com.dxc.ssi.agent.transport

import kotlinx.coroutines.channels.Channel

internal actual class PlatformSocket actual constructor(url: String) {
    actual fun openSocket(listener: PlatformSocketListener, socketOpenedChannel: Channel<Unit>) {
    }

    actual fun closeSocket(code: Int, reason: String) {
    }

    actual fun sendMessage(msg: String) {
    }
}
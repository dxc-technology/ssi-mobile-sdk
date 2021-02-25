package com.dxc.ssi.agent.transport

import java.lang.Thread

actual class Sleeper {
    actual fun sleep(value: Int) {
        Thread.sleep(value.toLong())
    }
}
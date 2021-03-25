package com.dxc.utils

actual class Sleeper {
    actual fun sleep(value: Int) {
        Thread.sleep(value.toLong())
    }
}
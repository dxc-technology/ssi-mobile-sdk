package com.dxc.utils

import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.posix.nanosleep
import platform.posix.timespec

actual class Sleeper actual constructor() {
    actual fun sleep(value: Int) {
        memScoped {
            val timeMillis = value.toLong()
            val timespec = alloc<timespec>()
            timespec.tv_sec = timeMillis / 1000
            timespec.tv_nsec = ((timeMillis % 1000L) * 1000000L).convert()
            nanosleep(timespec.ptr, null)
        }
    }
}
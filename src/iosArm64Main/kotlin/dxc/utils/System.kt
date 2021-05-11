package com.dxc.utils

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual class System {
    actual companion object {
        actual fun currentTimeMillis(): Long {
            return (NSDate().timeIntervalSince1970 * 1000).toLong()
        }
    }
}
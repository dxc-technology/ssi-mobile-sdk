package com.dxc.utils



actual class System {
    actual companion object {
        actual fun currentTimeMillis(): Long {
            return java.lang.System.currentTimeMillis()
        }
    }
}
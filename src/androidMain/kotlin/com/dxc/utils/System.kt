package com.dxc.utils

import java.lang.System


actual class System {
    actual companion object {
        actual fun currentTimeMillis(): Long {
            return java.lang.System.currentTimeMillis()
        }

        actual fun getEnv(key: String): String? {
            return System.getenv(key)
        }

        actual fun getProperty(key: String): String? {
            return System.getProperty(key)
        }
    }
}
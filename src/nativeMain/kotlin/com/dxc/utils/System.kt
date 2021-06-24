package com.dxc.utils

actual class System {
    actual companion object {
        actual fun currentTimeMillis(): Long {
            TODO("Not yet implemented")
        }

        actual fun getEnv(key: String): String? {
            TODO("Not yet implemented")
        }

        actual fun getProperty(key: String): String? {
            TODO("Not yet implemented")
        }

        actual fun setEnv(key: String, value: String) {
        }

        actual fun getCurrentThread(): String {
            TODO("Not yet implemented")
        }
    }
}
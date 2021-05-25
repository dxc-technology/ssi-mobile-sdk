package com.dxc.utils

import android.system.Os
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

        actual fun setEnv(key: String, value: String) {
            //Libcore.
            Os.setenv(key, value, true)
            //Libcore.os.setenv("VAR", "value", bOverwrite);
            //System.setProperty(key, value)

        }
    }
}
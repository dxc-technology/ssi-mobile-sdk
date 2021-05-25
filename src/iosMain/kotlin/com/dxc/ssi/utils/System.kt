package com.dxc.utils


import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import kotlinx.cinterop.toKString
import platform.Foundation.NSThread
import platform.posix.getenv
import platform.posix.setenv

actual class System {
    actual companion object {
        actual fun currentTimeMillis(): Long {
            return (NSDate().timeIntervalSince1970 * 1000).toLong()
        }

        actual fun getEnv(key: String): String? {
            return getenv("INDY_HOME")?.toKString()
        }

        actual fun getProperty(key: String): String? {
            //TODO: implement this separately if needed
            return getEnv(key)
        }

        actual fun setEnv(key: String, value: String) {
            setenv(key, value, 1)
        }

        actual fun getCurrentThread(): String {
           return  NSThread.currentThread.toString()
        }
    }
}
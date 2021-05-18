package com.dxc.utils


//TODO: think if this object can be moved to common layer
internal object EnvironmentUtils {
    val testPoolIP: String
        get() {
            val testPoolIp = System.getEnv("TEST_POOL_IP")
            return testPoolIp ?: "127.0.0.1"
        }

    internal val userHomePath: String get() = System.getProperty("INDY_HOME") ?: System.getEnv("HOME")!!

    fun getIndyHomePath(): String {
        return System.getIndyHomePath()
    }

    fun getIndyPoolPath(poolName: String) = getIndyHomePath() + "/pool/$poolName"

    fun getIndyWalletPath(walletName: String) = getIndyHomePath() + "/wallet/$walletName"

    fun getIndyHomePath(filename: String): String {
        return "${getIndyHomePath()}/$filename"
    }

    internal fun getTmpPath(): String {
        return System.getProperty("INDY_TMP") ?: System.getProperty("java.io.tmpdir") + "/indy"
    }

    internal fun getTmpPath(filename: String): String {
        return "${getTmpPath()}/$filename"
    }


}

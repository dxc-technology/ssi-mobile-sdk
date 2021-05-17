package com.dxc.utils

import com.dxc.ssi.agent.api.Environment


//TODO: Merge with PlatformInit
internal object EnvironmentUtils {

    lateinit var writableUserHomePath: String
    lateinit var indyHomePath: String

    fun getIndyPoolPath(poolName: String) = indyHomePath + "/pool/$poolName"

    fun getIndyWalletPath(walletName: String) = indyHomePath + "/wallet/$walletName"

    internal fun getTmpPath(): String {
        return System.getProperty("INDY_TMP") ?: System.getProperty("java.io.tmpdir") + "/indy"
    }

    internal fun getTmpPath(filename: String): String {
        return "${getTmpPath()}/$filename"
    }

    fun initEnvironment(environment: Environment) {
        writableUserHomePath = environment.getWritableFolderInUserHome()
        indyHomePath = "$writableUserHomePath/.indy_client"
        System.setEnv("INDY_HOME",indyHomePath)
    }
}

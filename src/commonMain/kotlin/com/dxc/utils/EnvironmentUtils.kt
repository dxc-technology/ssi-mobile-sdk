package com.dxc.utils

import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.api.Environment
import com.dxc.ssi.agent.wallet.indy.ObjectHolder


//TODO: Merge with PlatformInit
internal object EnvironmentUtils {

    private val isoIndyHomePath = IsolateState { ObjectHolder<String>() }
    private val isoWritableUserHomePath = IsolateState { ObjectHolder<String>() }

    var indyHomePath: String
        get() = isoIndyHomePath.access { it.obj }!!
        set(value) {
            isoIndyHomePath.access { it.obj = value }
        }

    var writableUserHomePath: String
        get() = isoWritableUserHomePath.access { it.obj }!!
        set(value) {
            isoWritableUserHomePath.access { it.obj = value }
        }

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
        System.setEnv("INDY_HOME", indyHomePath)
    }
}

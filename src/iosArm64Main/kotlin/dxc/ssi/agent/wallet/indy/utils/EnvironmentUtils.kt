package com.dxc.ssi.agent.wallet.indy.utils

import kotlinx.cinterop.toKString
import platform.posix.getenv

//TODO: think if this object can be moved to common layer
internal object EnvironmentUtils {

  //TODO: implement the rest of the utils and unify them across platform if possible
  /*
    val testPoolIP: String
        get() {
            val testPoolIp = System.getenv("TEST_POOL_IP")
            return testPoolIp ?: "127.0.0.1"
        }
*/
    //Should be similar to  RUST`s implementation
    private val userHomePath: String get() = getenv("INDY_HOME")?.toKString()?: getenv("HOME")?.toKString()!!

    fun getIndyHomePath(): String {
        return "$userHomePath/Documents/.indy_client"
    }

    fun getIndyPoolPath(poolName: String) = getIndyHomePath() + "/pool/$poolName"

    fun getIndyWalletPath(walletName: String) = getIndyHomePath() + "/wallet/$walletName"
/*
    fun getIndyHomePath(filename: String): String {
        return "${getIndyHomePath()}/$filename"
    }

    internal fun getTmpPath(): String {
        return System.getProperty("INDY_TMP") ?: System.getProperty("java.io.tmpdir") + "/indy"
    }

    internal fun getTmpPath(filename: String): String {
        return "${getTmpPath()}/$filename"
    }

    @Throws(IOException::class)
    internal fun createSymbolicLink(targetPath: Path, linkPath: Path) {
        if (Files.exists(linkPath)) {
            Files.delete(linkPath)
        }
        Files.createSymbolicLink(linkPath, targetPath)
    }
*/
}

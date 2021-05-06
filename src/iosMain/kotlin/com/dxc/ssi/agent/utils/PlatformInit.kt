package com.dxc.ssi.agent.utils

import platform.Foundation.NSHomeDirectory
import platform.posix.setenv

actual class PlatformInit {
    actual fun init() {
        val home = NSHomeDirectory()
        println("PlatformInit: setting INDY_HOME to $home")
        setenv("INDY_HOME",home,1)
    }
}
package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Environment
import platform.Foundation.NSHomeDirectory

actual class EnvironmentImpl : Environment {
    actual override fun getWritableFolderInUserHome(): String {
        val home = NSHomeDirectory()
        return "$home/Documents"
    }
}
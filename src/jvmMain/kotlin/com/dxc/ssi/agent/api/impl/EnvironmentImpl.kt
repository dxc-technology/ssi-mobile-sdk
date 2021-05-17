package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Environment

actual class EnvironmentImpl : Environment {
    actual override fun getWritableFolderInUserHome(): String {
        return System.getProperty("user.home")
    }
}
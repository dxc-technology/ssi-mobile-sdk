package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Environment

expect class EnvironmentImpl : Environment {
    override fun getWritableFolderInUserHome(): String
}

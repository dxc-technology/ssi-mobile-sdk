package com.dxc.ssi.agent.api

interface Environment {
    fun getWritableFolderInUserHome(): String
}
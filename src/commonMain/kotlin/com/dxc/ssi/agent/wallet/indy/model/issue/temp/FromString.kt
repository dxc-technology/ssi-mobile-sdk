package com.dxc.ssi.agent.wallet.indy.model.issue.temp

//TODO: structurize all code copied from cordentity
//TODO: Move it to package specific to indy ?
interface FromString<T : Any> {
    fun fromString(str: String): T
}
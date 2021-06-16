package com.dxc.ssi.agent.utils

import kotlin.coroutines.CoroutineContext

expect class SingleThreadContext {

    val context: CoroutineContext
    fun closeContext()

}
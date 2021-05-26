package com.dxc.ssi.agent.utils

import kotlinx.coroutines.SingleThreadDispatcher
import kotlin.coroutines.CoroutineContext

actual class SingleThreadContext(private val singleThreadDispatcher: SingleThreadDispatcher) {

    actual val context: CoroutineContext
        get() = singleThreadDispatcher

    actual fun closeContext() {
        singleThreadDispatcher.close()
    }

}
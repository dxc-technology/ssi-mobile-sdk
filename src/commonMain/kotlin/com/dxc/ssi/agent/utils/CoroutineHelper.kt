package com.dxc.ssi.agent.utils

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SingleThreadDispatcher
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class CoroutineHelper {
    companion object {
        fun <T> waitForCompletion(deferred: Deferred<T>): T {
            return runBlocking {
                deferred.await()
            }
        }
    }
}
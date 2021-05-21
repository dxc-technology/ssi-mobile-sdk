package com.dxc.ssi.agent.utils

import kotlinx.coroutines.Deferred
import kotlin.coroutines.CoroutineContext

expect class CoroutineHelper {
    companion object {
        //The reason this method exists is that we can not use runBlocking {} from common code in kotlin. This is kinda common wrapper for runBlocking
        fun <T> waitForCompletion(deferred: Deferred<T>):T

        //TODO: concurrency: currently this function might cause leaking threads as it requres closing the context. Understand where to close it properly
        fun  singleThreadCoroutineContext(threadName: String): CoroutineContext
    }
}

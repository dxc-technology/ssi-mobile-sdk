package com.dxc.ssi.agent.utils

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

actual class Waiter {
    actual companion object {
        actual fun <T> waitForCompletion(deferred: Deferred<T>):T {
            return  runBlocking {
                deferred.await()
            }
        }
    }
}
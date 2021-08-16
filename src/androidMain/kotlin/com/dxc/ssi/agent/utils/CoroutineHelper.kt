package com.dxc.ssi.agent.utils

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger

actual class CoroutineHelper {

    actual companion object {
        private val logger: Kermit = Kermit(LogcatLogger())
        actual fun <T> waitForCompletion(deferred: Deferred<T>): T {
            var result: T? = null
            try {
                runBlocking {
                    deferred.await()
                }.also { result = it }
            } catch (t: Throwable) {
                logger.e("Error from library", t) { t.message.toString() }
            }
            return result!!
        }

        actual fun singleThreadCoroutineContext(threadName: String): SingleThreadContext =
            SingleThreadContext(newSingleThreadContext(threadName))

    }
}
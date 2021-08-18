package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.utils.Sleeper
import kotlinx.coroutines.*
import platform.posix.sleep
import kotlin.test.Test
import kotlin.test.Ignore


class ConcurrencyTest {
    var logger: Kermit = Kermit(LogcatLogger())
    private var job = Job()
    private val agentScope = CoroutineScope(Dispatchers.Default + job)

    @Test
    // @Ignore
    fun test() {
        logger.d { "starting test" }
        runBlocking {
            logger.d { "starting runBlocking" }
            agentScope.async {
                logger.d { "starting async" }

                logger.d { "end async" }
            }.await()
            logger.d { "end runBlocking" }
        }
        logger.d { "end test" }
    }

    @Test
    // @Ignore
    fun test2() {



        logger.d { "starting test" }

        agentScope.launch {
            logger.d { "start sleeping in first sleeper" }
            while (true) {
                sleep(10)
                logger.d { "call delay in first sleeper" }
                delay(10)
            }
            logger.d { "end sleeping in first sleeper" }
        }

        logger.d { "after first launch" }

        agentScope.launch {
            logger.d { "start sleeping in second sleeper" }
            while (true) {
                sleep(10)
                logger.d { "call delay in second sleeper" }
                delay(10)
            }
            logger.d { "end sleeping in second sleeper" }
        }

        CoroutineHelper.waitForCompletion(agentScope.async {
            logger.d { "starting async" }

            logger.d { "end async" }
        })

        logger.d { "end test" }
    }


    @Test
    @Ignore
    fun testCustomCoroutineScope() {
        logger.d { "Test started" }


        val async = agentScope.async {
            logger.d { "executing agentScope.async" }
        }

        logger.d { "Waiting for async result" }
        runBlocking {
            async.await()
        }
        logger.d { "Got async result" }

        sleep(1000)
    }

    @Test
    @Ignore
    fun testGlobalScope() {
        logger.d { "Test started" }


        val async = GlobalScope.async {
            logger.d { "executing agentScope.async" }
        }

        logger.d { "Waiting for async result" }
        runBlocking {
            async.await()
        }
        logger.d { "Got async result" }

        sleep(1000)
    }


    private suspend fun listenForMessages() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for messages thread")) {
            logger.d { "IN listenForMessages function" }
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                logger.d { "stop listening for messages" }
            }

            logger.d { "after async" }
            //     }


        }
    }

    private suspend fun listenForFailures() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for failures thread")) {
            logger.d { "IN listenForFailures function" }
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                logger.d { "stop listening for failures" }
            }

            logger.d { "after async failures" }
            //     }


        }
    }
}
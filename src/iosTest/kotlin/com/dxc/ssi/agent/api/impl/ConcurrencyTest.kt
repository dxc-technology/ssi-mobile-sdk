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
        logger.log(Severity.Debug,"",null) { "starting test" }
        runBlocking {
            logger.log(Severity.Debug,"",null) { "starting runBlocking" }
            agentScope.async {
                logger.log(Severity.Debug,"",null) { "starting async" }

                logger.log(Severity.Debug,"",null) { "end async" }
            }.await()
            logger.log(Severity.Debug,"",null) { "end runBlocking" }
        }
        logger.log(Severity.Debug,"",null) { "end test" }
    }

    @Test
    // @Ignore
    fun test2() {



        logger.log(Severity.Debug,"",null) { "starting test" }

        agentScope.launch {
            logger.log(Severity.Debug,"",null) { "start sleeping in first sleeper" }
            while (true) {
                sleep(10)
                logger.log(Severity.Debug,"",null) { "call delay in first sleeper" }
                delay(10)
            }
            logger.log(Severity.Debug,"",null) { "end sleeping in first sleeper" }
        }

        logger.log(Severity.Debug,"",null) { "after first launch" }

        agentScope.launch {
            logger.log(Severity.Debug,"",null) { "start sleeping in second sleeper" }
            while (true) {
                sleep(10)
                logger.log(Severity.Debug,"",null) { "call delay in second sleeper" }
                delay(10)
            }
            logger.log(Severity.Debug,"",null) { "end sleeping in second sleeper" }
        }

        CoroutineHelper.waitForCompletion(agentScope.async {
            logger.log(Severity.Debug,"",null) { "starting async" }

            logger.log(Severity.Debug,"",null) { "end async" }
        })

        logger.log(Severity.Debug,"",null) { "end test" }
    }


    @Test
    @Ignore
    fun testCustomCoroutineScope() {
        logger.log(Severity.Debug,"",null) { "Test started" }


        val async = agentScope.async {
            logger.log(Severity.Debug,"",null) { "executing agentScope.async" }
        }

        logger.log(Severity.Debug,"",null) { "Waiting for async result" }
        runBlocking {
            async.await()
        }
        logger.log(Severity.Debug,"",null) { "Got async result" }

        sleep(1000)
    }

    @Test
    @Ignore
    fun testGlobalScope() {
        logger.log(Severity.Debug,"",null) { "Test started" }


        val async = GlobalScope.async {
            logger.log(Severity.Debug,"",null) { "executing agentScope.async" }
        }

        logger.log(Severity.Debug,"",null) { "Waiting for async result" }
        runBlocking {
            async.await()
        }
        logger.log(Severity.Debug,"",null) { "Got async result" }

        sleep(1000)
    }


    private suspend fun listenForMessages() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for messages thread")) {
            logger.log(Severity.Debug,"",null) { "IN listenForMessages function" }
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                logger.log(Severity.Debug,"",null) { "stop listening for messages" }
            }

            logger.log(Severity.Debug,"",null) { "after async" }
            //     }


        }
    }

    private suspend fun listenForFailures() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for failures thread")) {
            logger.log(Severity.Debug,"",null) { "IN listenForFailures function" }
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                logger.log(Severity.Debug,"",null) { "stop listening for failures" }
            }

            logger.log(Severity.Debug,"",null) { "after async failures" }
            //     }


        }
    }
}
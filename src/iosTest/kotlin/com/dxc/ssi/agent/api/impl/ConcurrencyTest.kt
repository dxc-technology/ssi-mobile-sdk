package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.utils.Sleeper
import kotlinx.coroutines.*
import platform.posix.sleep
import kotlin.test.Test
import kotlin.test.Ignore


class ConcurrencyTest {

    private var job = Job()
    private val agentScope = CoroutineScope(Dispatchers.Default + job)

    @Test
    // @Ignore
    fun test() {
        println("starting test")
        runBlocking {
            println("starting runBlocking")
            agentScope.async {
                println("starting async")

                println("end async")
            }.await()
            println("end runBlocking")
        }
        println("end test")
    }

    @Test
    // @Ignore
    fun test2() {



        println("starting test")

        agentScope.launch {
            println("start sleeping in first sleeper")
            while (true) {
                sleep(10)
                println("call delay in first sleeper")
                delay(10)
            }
            println("end sleeping in first sleeper")
        }

        println("after first launch")

        agentScope.launch {
            println("start sleeping in second sleeper")
            while (true) {
                sleep(10)
                println("call delay in second sleeper")
                delay(10)
            }
            println("end sleeping in second sleeper")
        }

        CoroutineHelper.waitForCompletion(agentScope.async {
            println("starting async")

            println("end async")
        })

        println("end test")
    }


    @Test
    @Ignore
    fun testCustomCoroutineScope() {
        println("Test started")


        val async = agentScope.async {
            println("executing agentScope.async")
        }

        println("Waiting for async result")
        runBlocking {
            async.await()
        }
        println("Got async result")

        sleep(1000)
    }

    @Test
    @Ignore
    fun testGlobalScope() {
        println("Test started")


        val async = GlobalScope.async {
            println("executing agentScope.async")
        }

        println("Waiting for async result")
        runBlocking {
            async.await()
        }
        println("Got async result")

        sleep(1000)
    }


    private suspend fun listenForMessages() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for messages thread")) {
            println("IN listenForMessages function")
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                println("stop listening for messages")
            }

            println("after async")
            //     }


        }
    }

    private suspend fun listenForFailures() {

        //TODO: check how to close this context afterwards to avoid leaking native threads
        withContext(newSingleThreadContext("listen for failures thread")) {
            println("IN listenForFailures function")
            //TODO: change to smth like while CONNECTED
            //    withContext(newSingleThreadContext("Thread 2")) {
            async {
                Sleeper().sleep(10000)
                println("stop listening for failures")
            }

            println("after async failures")
            //     }


        }
    }
}
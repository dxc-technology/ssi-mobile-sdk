package com.dxc.ssi.agent.api.impl

import com.dxc.utils.Sleeper
import kotlinx.coroutines.*
import platform.posix.sleep
import kotlin.test.Test
import kotlin.test.Ignore

class ConcurrencyTest {

    private var job = Job()
    private val agentScope = CoroutineScope(Dispatchers.Default + job)

    @Test
    @Ignore
    fun test() {

        runBlocking {

            agentScope.async {
                println("starting test")
                async { listenForMessages() }
                async { listenForFailures() }
                println("after listening")
            }.await()

        }


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
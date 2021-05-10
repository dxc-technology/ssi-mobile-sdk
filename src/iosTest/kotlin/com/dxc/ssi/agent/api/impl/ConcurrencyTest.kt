package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.transport.Sleeper
import kotlinx.coroutines.*
import kotlin.test.Test
import kotlin.test.Ignore

class ConcurrencyTest {

    @Test
    @Ignore
    fun test() {

        runBlocking {

            GlobalScope.async {
                println("starting test")
                async { listenForMessages() }
                async { listenForFailures() }
                println("after listening")
            }.await()

        }


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
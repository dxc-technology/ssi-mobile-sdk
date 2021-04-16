package com.dxc.ssi.agent.callback

import co.touchlab.stately.collections.sharedMutableMapOf
import com.dxc.ssi.agent.exceptions.indy.IndyException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlin.native.concurrent.AtomicInt

@SharedImmutable
val callbackHandler = CallbackHandler()

data class TestCallbackDataImpl(
    override val commandHandle: Int,
    override val errorCode: UInt
) : CallbackData


class CallbackHandler() {

    companion object {
        val commandHandleCounter: AtomicInt = AtomicInt(1)
    }

    //TODO: see if it is possible to avoid having two maps and put all results into CompletableDeferred
    private val activeCallbackDataMap = sharedMutableMapOf<Int, CallbackData>()
    private val activeCallbackStatusMap = sharedMutableMapOf<Int, CompletableDeferred<IndyException?>>()

    fun prepareCallback(): Int {
        val commandHandle = commandHandleCounter.value++
        if (activeCallbackStatusMap[commandHandle] != null
            || activeCallbackDataMap[commandHandle] != null
        ) throw IllegalStateException("Attempt to prepare callback for commandHandle = $commandHandle which is already exist")

        activeCallbackStatusMap[commandHandle] = CompletableDeferred()
        return commandHandle
    }

    fun setCallbackResult(callbackData: CallbackData) {

        val commandHandle = callbackData.commandHandle

        if (activeCallbackStatusMap[commandHandle] == null)
            throw IllegalStateException("Attempt to set callback result for unprepared callback $commandHandle")

        if (activeCallbackDataMap[commandHandle] != null)
            throw IllegalStateException("Messed up state between StatusMap and DataMap for commandHandle $commandHandle")


        activeCallbackDataMap[commandHandle] = callbackData
        activeCallbackStatusMap[commandHandle]!!.complete(
            if (callbackData.errorCode == 0U) {
                null
            } else {
                //ErrorDetailsWithCode(callbackData.errorCode.toInt())
                IndyException.fromSdkError(callbackData.errorCode.toInt())
            }
        )
    }

    fun waitForCallbackResult(commandHandle: Int): CallbackData {

        //TODO: before throwing exception ensure to clear cache
        if (activeCallbackStatusMap[commandHandle] == null)
            throw IllegalStateException("Attempt to get callback result for unprepared callback $commandHandle")

        //TODO: think if we need introduce timeout here

        val indyException = runBlocking {
            activeCallbackStatusMap[commandHandle]!!.await()
        }
        //TODO: before throwing exception ensure to clear cache
        if (activeCallbackDataMap[commandHandle] == null)
            throw IllegalStateException("Messed up state between StatusMap and DataMap for commandHandle $commandHandle")


        val callbackData = activeCallbackDataMap[commandHandle]!!

        //TODO: before throwing exception ensure to clear cache
        //  validateCallbackResult(callbackData.errorCode)

        activeCallbackDataMap.remove(commandHandle)
        activeCallbackStatusMap.remove(commandHandle)

        indyException?.let { e ->
            println("Received IndyException $e")
            throw e }

        return callbackData!!

    }

    private fun validateCallbackResult(errorCode: UInt) {
        if (errorCode != 0U) {
            throw IndyException.fromSdkError(errorCode.toInt())

        }
    }

}
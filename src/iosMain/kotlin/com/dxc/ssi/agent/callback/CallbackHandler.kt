package com.dxc.ssi.agent.callback

import co.touchlab.stately.collections.sharedMutableMapOf
import com.dxc.ssi.agent.exceptions.indy.IndyException
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import kotlinx.coroutines.CompletableDeferred
import kotlin.native.concurrent.AtomicInt

@SharedImmutable
val callbackHandler = CallbackHandler()

data class CallbackDataWrapper(
    val callbackData: CallbackData,
    val indyException: IndyException?
)


class CallbackHandler() {

    private val logger: Kermit = Kermit(LogcatLogger())
    companion object {
        val commandHandleCounter: AtomicInt = AtomicInt(1)
    }

    private val activeCallbacksMap = sharedMutableMapOf<Int, CompletableDeferred<CallbackDataWrapper>>()

    fun prepareCallback(): Int {
        val commandHandle = commandHandleCounter.value++
        if (activeCallbacksMap[commandHandle] != null) throw IllegalStateException("Attempt to prepare callback for commandHandle = $commandHandle which is already exist")

        activeCallbacksMap[commandHandle] = CompletableDeferred()
        return commandHandle
    }

    fun setCallbackResult(callbackData: CallbackData) {

        val commandHandle = callbackData.commandHandle

        if (activeCallbacksMap[commandHandle] == null)
            throw IllegalStateException("Attempt to set callback result for unprepared callback $commandHandle")


        activeCallbacksMap[commandHandle]!!.complete(
            CallbackDataWrapper(
                callbackData = callbackData,
                indyException = if (callbackData.errorCode == 0U) null
                 else IndyException.fromSdkError(callbackData.errorCode.toInt())))
    }

    suspend fun waitForCallbackResult(commandHandle: Int): CallbackData {

        if (activeCallbacksMap[commandHandle] == null)
            throw IllegalStateException("Attempt to get callback result for unprepared callback $commandHandle")

        //TODO: think if we need introduce timeout here
        val callbackDataWrapper =
            activeCallbacksMap[commandHandle]!!.await()


        activeCallbacksMap.remove(commandHandle)

        callbackDataWrapper.indyException?.let { e ->
            logger.e { "Received IndyException $e" }
            throw e
        }

        return callbackDataWrapper.callbackData
    }

}
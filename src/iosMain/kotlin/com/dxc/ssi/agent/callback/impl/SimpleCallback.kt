package com.dxc.ssi.agent.callback.impl

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import kotlinx.cinterop.staticCFunction

//Use this object everywhere when callback does not return any parameters
object SimpleCallback {

    val callback =
        staticCFunction() { commandHandle: Int, errorCode: UInt
            ->
            initRuntimeIfNeeded()
            callbackHandler.setCallbackResult(
                Result(commandHandle, errorCode)
            )
        }

    data class Result(
        override val commandHandle: Int,
        override val errorCode: UInt
    ) : CallbackData

}


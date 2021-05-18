package com.dxc.ssi.agent.callback.impl

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString

//Use this object everywhere when callback returns single string
object IntCallback {

    val callback =
        staticCFunction() { commandHandle: Int, errorCode: UInt, handle: Int
            ->
            initRuntimeIfNeeded()
            //TODO: deal with the NPE. Probably making Result.stringResult to be nullable would be good solution
            callbackHandler.setCallbackResult(
                Result(commandHandle, errorCode, handle)
            )
        }

    data class Result(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val handle: Int
    ) : CallbackData

}


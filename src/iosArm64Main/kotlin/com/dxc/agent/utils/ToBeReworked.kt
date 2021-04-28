package com.dxc.ssi.agent.utils

import com.indylib.indy_bool_t
import com.indylib.indy_set_logger
import com.indylib.indy_u32_t
import kotlinx.cinterop.*
import platform.posix.sleep

object ToBeReworked {
//TODO: place it in to appropriate package
    //TODO: rewrite this functions to use kotlin data types
    fun enableIndyLog() {
        val context: CValuesRef<*>? = null
        val enabledFn: CPointer<CFunction<(
            COpaquePointer?, indy_u32_t,
            CPointer<ByteVar>?
        ) -> indy_bool_t>>? = null
        val flushFn: CPointer<CFunction<(COpaquePointer?) -> Unit>>? = null
        val myExitCallback = staticCFunction(fun(
            log: CPointer<out CPointed>?,
            elem: indy_u32_t,
            pointer: CPointer<ByteVar>?,
            val1: CPointer<ByteVar>?,
            val2: CPointer<ByteVar>?,
            val3: CPointer<ByteVar>?,
            number: indy_u32_t,
        ) {
            initRuntimeIfNeeded()
            println(pointer?.toKString())
            println(val1?.toKString())
            println(val2?.toKString())
            println(val3?.toKString())
            return
        })
        indy_set_logger(
            context,
            enabledFn,
            myExitCallback,
            flushFn
        )
        sleep(6)
    }

}
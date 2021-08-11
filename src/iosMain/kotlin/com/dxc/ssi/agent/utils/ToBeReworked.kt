package com.dxc.ssi.agent.utils

import com.indylib.indy_bool_t
import com.indylib.indy_set_logger
import com.indylib.indy_u32_t
import kotlinx.cinterop.*
import platform.posix.sleep
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity

object ToBeReworked {
    var logger: Kermit = Kermit(LogcatLogger())
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
            logger.log(Severity.Debug,"",null) { pointer?.toKString()!! }
            logger.log(Severity.Debug,"",null) { val1?.toKString()!! }
            logger.log(Severity.Debug,"",null) { val2?.toKString()!! }
            logger.log(Severity.Debug,"",null) { val3?.toKString()!! }
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
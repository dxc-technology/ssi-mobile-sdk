package com.dxc.ssi.agent.api.impl

import com.indylib.*
import kotlinx.cinterop.*
import platform.posix.sleep
import kotlin.native.ref.WeakReference
import kotlin.test.Test

typealias MyCallbackWallet = CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>?
typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>
typealias MyCallbackWallet2 = CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>>

var wk: WeakReference<String> = WeakReference("empty")
fun add(data: String?) {
    //memScoped {
    initRuntimeIfNeeded()
    println("call " + data)
    println("call " + data)
    //val res: String? = data
    //val arr = res!![0]
    wk = WeakReference(data!!)
    //}
}

var d: indy_handle_t = 0
fun print(): String? {
    return wk.get()
}

class IosIndyTest {

    // #define levelMappings @{@"1": @"Error", @"2": @"Warning", @"3": @"Info", @"4": @"Debug", @"5": @"Trace"}
    //    [IndyLogger setLogger:^(NSObject *context, NSNumber *level, NSString *target, NSString *message, NSString *modulePath, NSString *file, NSNumber *line) {
    //    NSLog(@"%@    %@:%@ | %@", [levelMappings valueForKey:[NSString stringWithFormat:@"%@", level]], file, line, message);
    //}];
    @Test
    fun test_indy_log() {

        val context: CValuesRef<*>? = null
        val enabledFn: CPointer<CFunction<(
            COpaquePointer?, indy_u32_t,
            CPointer<ByteVar>?
        ) -> indy_bool_t>>? = null
        val flushFn: CPointer<CFunction<(COpaquePointer?) -> Unit>>? = null
        val myExitCallback = staticCFunction(fun(
            log: CPointer<out CPointed>?,
            elem: indy_u32_t,
            pointer: CPointer<ByteVarOf<Byte>>?,
            val1: CPointer<ByteVar>?,
            val2: CPointer<ByteVar>?,
            val3: CPointer<ByteVar>?,
            number: indy_u32_t, /* = UInt */
        ) {
            println("log $log")
            println("elem $elem")
            println("pointer $pointer")
            println("val1 " + val1?.toKString())
            println("val2 " + val2?.toKString())
            println("val3 " + val3?.toKString())
            println("number $number")
            return
        })
        val result = indy_set_logger(
            context,
            enabledFn,
            myExitCallback,
            flushFn
        )
        println("indy_log $result")
        sleep(10)
    }
    // #define levelMappings @{@"1": @"Error", @"2": @"Warning", @"3": @"Info", @"4": @"Debug", @"5": @"Trace"}

    //    [IndyLogger setLogger:^(NSObject *context, NSNumber *level, NSString *target, NSString *message, NSString *modulePath, NSString *file, NSNumber *line) {
//        NSLog(@"%@    %@:%@ | %@", [levelMappings valueForKey:[NSString stringWithFormat:@"%@", level]], file, line, message);
//    }];
//
//    @Test
//    fun test_indy_log() {
//        val pointer = "119"
//        var indy_get_logger: CValuesRef<*>? = null
//        var enabledFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) -> indy_bool_t /* = Boolean */>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) → indy_bool_t /* = Boolean */>>> */>? =
//            null
//        var logFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) → Unit>>> */>? =
//            null
//        var flushFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) → Unit>>> */>? =
//            null
//
//        val myExit_cb = staticCFunction(fun(
//            indy_get_logger: CValuesRef<*>?,   //e: /Users/kkamyczek/ssi-mobile_final/ssi-mobile-sdk/src/iosX64Test/kotlin/com/dxc/ssi/agent/api/impl/SimpleTest.kt: (44, 25): type kotlinx.cinterop.CValuesRef<*>?  of callback parameter 1 is not supported here: doesn't correspond to any C type
//            enabledFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) -> indy_bool_t /* = Boolean */>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) → indy_bool_t /* = Boolean */>>> */>?,
//            logFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) → Unit>>> */>?,
//            flushFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) → Unit>>> */>?
//        ) {
//            println(indy_get_logger)
//            println(enabledFn)
//            println(logFn)
//            println(flushFn)
//            return
//        })
//        val result = indy_get_logger(
//            indy_get_logger,
//            enabledFn,
//            logFn,
//            flushFn
//        )
//        println("indy_create_wallet " + result)
//        sleep(10)
//    }
    @Test
    fun run() {
        val didJson = "{}"
        val commandHandle = 0
        val walletHandle = 0
        val callback: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println(did?.toKString())
            println(verkey?.toKString())
            println(xcommand_handle)
            println(err)
            return
        })
        val exitCode: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            walletHandle,
            didJson,
            callback
        )
        println(exitCode)
        sleep(5)
    }

    @Test
    fun test_indy_create_wallet() {
        val command = 1
        val pointer = "121"
        val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
        val myExit_cb: MyCallbackWallet = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
        ) {
            println("wallet")
            println(xcommand_handle)
            println(err)
//            d = xcommand_handle
            return
        })
        val result: indy_error_t = indy_create_wallet(
            command,
            config,
            credentials,
            myExit_cb
        )
        println("indy_create_wallet " + result)
        sleep(10)

        val myExit_cb2: MyCallbackWallet2 = staticCFunction(fun(
            command: indy_handle_t,
            err: indy_error_t,
            handle: indy_handle_t
        ) {
            println("wallet")
            println(command)
            println(handle)
            println(err)
            return
        })
        val result2: indy_error_t = indy_open_wallet(
            command,
            config,
            credentials,
            myExit_cb2
        )
        println("indy_open_wallet " + result2)
        sleep(10)
        val didJson = "{}"
        val commandHandle = 1
        val wallethandle = 3
        val callback: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println("did")
            println(did?.toKString())
            println(verkey?.toKString())
            println(xcommand_handle)
            println(err)
            add(did?.toKString())
            return
        })
        val exitCode: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            wallethandle,
            didJson,
            callback
        )
        sleep(10)
        println(print())
        println("indy_create_and_store_my_did " + exitCode)
        test_indy_log()
    }

    @Test
    fun test_indy_create_and_store_my_did() {

        val commandHandle = 1
        val walletHandle = 3
        val didJson = "{}"
        val myExit_cb2: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println("did")
            println(xcommand_handle)
            println(err)
            println(did?.toKString())
            println(verkey?.toKString())
            println(did!!.getRawValue())
            //a = did!!.getRawValue()
            println(interpretCPointer<ByteVar>(did.getRawValue())?.toKString())
            //a = verkey?.toKString()
            //nativeMemUtils.putByteArray()
            return
        })
        val result2: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            walletHandle,
            didJson,
            myExit_cb2
        )
        assert(result2.toInt().equals(0))
        println(result2)
        //println(a)
        sleep(8)
        //println(a)
    }
}



package com.dxc.ssi.agent.api.impl

import com.indylib.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicReference
import kotlin.test.Test

@SharedImmutable
val rw = ReadWrite()

class ReadWrite {
    fun String.nsdata(): NSData? =
        NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

    fun NSData.string(): String? =
        NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

    var atomic: AtomicReference<NSData?> = AtomicReference("".nsdata())
    fun read(): String {
        return atomic.value?.string()!!
    }
    fun save(text: String) {
        atomic.value = text.nsdata()
    }
}
typealias MyCallbackWallet = CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>?
typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>
typealias MyCallbackWallet2 = CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>>

class IosIndyTest {

    @Test
    fun test_indy_log() {

        memScoped {
            val context: CValuesRef<*>? = null
            val enabledFn2: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) -> indy_bool_t /* = Boolean */>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) → indy_bool_t /* = Boolean */>>> */>? =
                null
            val logFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) → Unit>>> */>? =
                null
            val flushFn2: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) → Unit>>> */>? =
                null
            indy_get_logger(
                context,
                enabledFn2,
                logFn,
                flushFn2
            )
            sleep(10)
            val command = 1
            val pointer = "132"
            val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
            val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
            val myExit_cb: MyCallbackWallet = staticCFunction(fun(
                xcommand_handle: indy_handle_t,
                err: indy_error_t,
            ) {
                return
            })
            indy_create_wallet(
                command,
                config,
                credentials,
                myExit_cb
            )
            sleep(10)
        }
    }

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
        val pointer = "167"
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
            return
        })
        indy_set_logger(
            context,
            enabledFn,
            myExitCallback,
            flushFn
        )
        sleep(8)
        val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
        val myExit_cb: MyCallbackWallet = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
        ) {
            initRuntimeIfNeeded()
            return
        })
        indy_create_wallet(
            command,
            config,
            credentials,
            myExit_cb
        )
        sleep(8)
        val myExit_cb2: MyCallbackWallet2 = staticCFunction(fun(
            command: indy_handle_t,
            err: indy_error_t,
            handle: indy_handle_t
        ) {
            initRuntimeIfNeeded()
            return
        })
        indy_open_wallet(
            command,
            config,
            credentials,
            myExit_cb2
        )

        sleep(8)
        val didJson = "{}"
        val commandHandle = 1
        val wallethandle = 3
        memScoped {
            val callback: MyCallback = staticCFunction(fun(
                xcommand_handle: indy_handle_t,
                err: indy_error_t,
                did: CPointer<ByteVar>?,
                verkey: CPointer<ByteVar>?
            ) {
                initRuntimeIfNeeded()
                val didData: String? = did?.toKString()
                val verkeyData: String? = verkey?.toKString()
                println("Print:Did:${didData} VerKey:${verkeyData}")
                rw.save("Did:${didData} VerKey:${verkeyData}")
                return
            })
            indy_create_and_store_my_did(
                commandHandle,
                wallethandle,
                didJson,
                callback
            )
            sleep(8)
            println(rw.read())
        }
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
            println(interpretCPointer<ByteVar>(did.getRawValue())?.toKString())
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
        sleep(8)
    }
}




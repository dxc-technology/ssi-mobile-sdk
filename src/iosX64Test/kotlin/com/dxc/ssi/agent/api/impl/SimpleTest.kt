package com.dxc.ssi.agent.api.impl

import com.indylib.*
import kotlinx.cinterop.*
import platform.posix.sleep
import kotlin.test.Test

typealias MyCallbackWallet = CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>?
typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>
typealias MyCallbackWallet2 = CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>>


class IosIndyTest {

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
        val config = "{\"id\":\"testWalletName14\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword14\"}"
        val myExit_cb: MyCallbackWallet = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
        ) {
            println("wallet")
            println(xcommand_handle)
            println(err)
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
            return
        })
        val exitCode: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            wallethandle,
            didJson,
            callback
        )
        sleep(10)
        println("indy_create_and_store_my_did " + exitCode)
    }

    @Test
    fun test_indy_create_and_store_my_did() {
        val commandHandle = 4
        val walletHandle = 1
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
        sleep(4)
    }
}

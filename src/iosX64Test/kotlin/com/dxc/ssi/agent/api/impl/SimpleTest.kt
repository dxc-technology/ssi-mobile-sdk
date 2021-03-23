package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.wallet.indy.MyCallback
import com.indylib.*
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import platform.posix.sleep
import kotlin.test.Test

typealias MyCallbackWallet = CPointer<kotlinx.cinterop.CFunction<(indy_handle_t, indy_error_t) -> Unit>>?

class IosIndyTest {

    @Test
    fun run() {

        val command = 4
        val config = "{\"id\":\"testWalletName\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword\"}"
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
        println(result)
        sleep(4)

        val commandHandle: Int = 4 //= addFuture(future)
        val walletHandle: Int = result.toInt() //wallet.getWalletHandle()
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
        println(result2)
        sleep(4)
    }

//    @Test
//    fun run2() {
//        val command_handle = 1
//        val wallet_handle = 1
//        val did_json = "{}"
//        println(command_handle)
//        val elem = null
//        var indyCreateAndStoreMyDid = indy_create_and_store_my_did(command_handle, wallet_handle, did_json, elem)
//        println(indyCreateAndStoreMyDid)
//    }
}
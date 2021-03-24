package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.wallet.indy.MyCallback
import com.indylib.indy_create_and_store_my_did
import com.indylib.indy_create_wallet
import com.indylib.indy_error_t
import com.indylib.indy_handle_t
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import platform.posix.sleep
import kotlin.test.Test

typealias MyCallbackWallet = CPointer<kotlinx.cinterop.CFunction<(indy_handle_t, indy_error_t) -> Unit>>?

class IosIndyTest {

    @Test
    fun test_indy_create_wallet() {

        val command = 4
        val config = "{\"id\":\"testWalletName2\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword2\"}"
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
        assert(result.toInt().equals(0))
        sleep(4)
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

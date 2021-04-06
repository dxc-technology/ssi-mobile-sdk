package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicReference

class ReadWrite {
    private var atomicVk: AtomicReference<NSData?> = AtomicReference("".nsdata())
    private var atomicDid: AtomicReference<NSData?> = AtomicReference("".nsdata())

    fun String.nsdata(): NSData? =
        NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

    fun NSData.string(): String? =
        NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

    fun readDid(): String {
        return atomicDid.value?.string()!!
    }

    fun readVk(): String {
        return atomicVk.value?.string()!!
    }

    fun save(did: String?, vk: String?) {
        atomicDid.value = did?.nsdata()
        atomicVk.value = vk?.nsdata()
    }
}

@SharedImmutable
val rw = ReadWrite()

actual class Did {
    actual companion object {
        actual fun createAndStoreMyDid(
            wallet: Wallet,
            didJson: String
        ): CreateAndStoreMyDidResult {
            memScoped {
                val command = 1
                val pointer = "167"
                val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
                val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
                val myExit_cb: CPointer<CFunction<(indy_handle_t /* = Int */, indy_error_t /* = UInt */) -> Unit>>? = staticCFunction(fun(
                    handle: indy_handle_t,
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
                sleep(8)
                val myExit_cb2: CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>> =
                    staticCFunction(fun(
                        command: indy_handle_t,
                        err: indy_error_t,
                        handle: indy_handle_t
                    ) {
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

                val callback: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>> =
                    staticCFunction(fun(
                        xcommand_handle: indy_handle_t,
                        err: indy_error_t,
                        did: CPointer<ByteVar>?,
                        verkey: CPointer<ByteVar>?
                    ) {
                        initRuntimeIfNeeded()
                        val didData: String? = did?.toKString()
                        val verkeyData: String? = verkey?.toKString()
                        println("Print:Did:${didData} VerKey:${verkeyData}")
                        rw.save(didData, verkeyData)
                        return
                    })
                val result = indy_create_and_store_my_did(
                    commandHandle,
                    wallethandle,
                    didJson,
                    callback
                )
                sleep(8)

                val d = rw.readDid()
                val vk = rw.readVk()
                if (result.toInt() != 0)
                    throw WalletItemNotFoundException()
                else
                    return CreateAndStoreMyDidResult(
                        rw.readDid(),
                        rw.readVk()
                    )
            }
        }
    }
}
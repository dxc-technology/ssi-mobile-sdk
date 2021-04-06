package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.indy_create_and_store_my_did
import com.indylib.indy_error_t
import com.indylib.indy_handle_t
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicInt
import kotlin.native.concurrent.AtomicReference

@SharedImmutable
val rw = ReadWrite()

class ReadWrite {
    var atomicVk: AtomicReference<NSData?> = AtomicReference("".nsdata())
    var atomicDid: AtomicReference<NSData?> = AtomicReference("".nsdata())

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

typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>

@ThreadLocal
private var atomicInteger: AtomicInt = AtomicInt(1)

actual class Did {
    actual companion object {
        actual fun createAndStoreMyDid(
            wallet: Wallet,
            didJson: String
        ): CreateAndStoreMyDidResult {

            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
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
                    rw.save(didData, verkeyData)
                    return
                })
                indy_create_and_store_my_did(
                    commandHandle,
                    walletHandle,
                    didJson,
                    callback
                )
                sleep(8)

                return CreateAndStoreMyDidResult(rw.readDid(), rw.readVk())
            }
        }
    }
}
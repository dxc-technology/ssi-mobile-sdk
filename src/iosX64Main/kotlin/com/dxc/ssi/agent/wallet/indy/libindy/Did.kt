package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.indy_create_and_store_my_did
import com.indylib.indy_error_t
import com.indylib.indy_handle_t
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

                val commandHandle = Api.atomicInteger.value++
                val walletHandle = wallet.getWalletHandle()

                val callback: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>> =
                    staticCFunction(fun(
                        _: indy_handle_t,
                        _: indy_error_t,
                        did: CPointer<ByteVar>?,
                        verifiable: CPointer<ByteVar>?
                    ) {
                        initRuntimeIfNeeded()
                        val didData: String? = did?.toKString()
                        val verkeyData: String? = verifiable?.toKString()
                        rw.save(didData, verkeyData)
                        return
                    })
                val result = indy_create_and_store_my_did(
                    commandHandle,
                    walletHandle,
                    didJson,
                    callback
                )
                sleep(8)

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
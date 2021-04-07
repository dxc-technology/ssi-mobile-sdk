package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.wallet.indy.libindy.Api.Companion.atomicInteger
import com.indylib.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicReference

class ReadWriteData {
    private var atomic: AtomicReference<NSData?> = AtomicReference("".nsdata())

    fun String.nsdata(): NSData? =
        NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

    fun NSData.string(): String? =
        NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

    fun read(): String {
        return atomic.value?.string()!!
    }

    fun write(text:String?) {
        atomic.value = text?.nsdata()
    }
}

@SharedImmutable
val readwriter = ReadWriteData()

actual class WalletRecord {
    actual companion object {
        actual fun get(
            wallet: Wallet,
            type: String,
            id: String,
            optionsJson: String
        ): String {
            memScoped {
                val walletHandle = wallet.getWalletHandle()
                val commandHandle = atomicInteger.value++
                val myExitCallbackOpen: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?) -> Unit>> =
                    staticCFunction(fun(
                        _: indy_handle_t,
                        _: indy_error_t,
                        data: CPointer<ByteVar>?
                    ) {
                        initRuntimeIfNeeded()
                        val didData: String? = data?.toKString()
                        readwriter.write(didData)
                        return
                    })
                val result: indy_error_t = indy_get_wallet_record(
                    commandHandle,
                    walletHandle,
                    type,
                    id,
                    optionsJson,
                    myExitCallbackOpen
                )

                sleep(8)
                if (result.toInt() != 0 || readwriter.read() != null)
                    throw Exception("WalletItemNotFoundException")
                return readwriter.read()
            }
        }

        actual fun add(
            wallet: Wallet,
            type: String,
            id: String,
            value: String,
            tagsJson: String?
        ) {
            val myExitCallbackAdd: CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    _: indy_error_t
                ) {
                    return
                })
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
            val result = indy_add_wallet_record(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                tagsJson,
                myExitCallbackAdd
            )
            sleep(8)
            if (result.toInt() != 0)
                throw Exception("WalletAddException")
            return
        }

        actual fun updateValue(
            wallet: Wallet,
            type: String,
            id: String,
            value: String
        ) {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = atomicInteger.value++
            val myExitCallbackUpdate: CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    _: indy_error_t
                ) {
                    return
                })
            val result = indy_update_wallet_record_value(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                myExitCallbackUpdate
            )
            sleep(8)
            if (result.toInt() != 0)
                throw Exception("WalletUpdateException")
            return
        }
    }
}
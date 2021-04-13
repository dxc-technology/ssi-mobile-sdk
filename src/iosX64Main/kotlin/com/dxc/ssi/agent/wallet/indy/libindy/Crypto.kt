package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.*
import io.ktor.utils.io.core.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.memcpy
import platform.posix.sleep
import kotlin.native.concurrent.AtomicReference

class SimpleReadWrite {
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

@SharedImmutable
val rwStringPack = SimpleReadWrite()

@SharedImmutable
val rwStringUnPack = SimpleReadWrite()

actual class Crypto {
    actual companion object {
        @ExperimentalUnsignedTypes
        actual fun packMessage(
            wallet: Wallet,
            recipientVk: String,
            senderVk: String?,
            message: ByteArray
        ): ByteArray {
            memScoped {
                val string: String = message.decodeToString(0, message.size, true)
                val recipientVkData: String = recipientVk
                val senderVkData: String? = senderVk
                val walletHandle = wallet.getWalletHandle()
                val commandHandle = Api.atomicInteger.value++
                val reference =
                    string.cstr as CValuesRef<indy_u8_tVar>?
                val size = string.length.toUInt()
                val packMessageCb: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<indy_u8_tVar>?, indy_u32_t) -> Unit>>? =
                    staticCFunction(fun(
                        _: indy_handle_t,
                        error: indy_error_t,
                        data: CPointer<indy_u8_tVar>?,
                        size: indy_u32_t
                    ) {
                        initRuntimeIfNeeded()
                        val byte: ByteArray = ByteArray(size.toInt()).apply {
                            usePinned {
                                memcpy(it.addressOf(0), data, size.toULong())
                            }
                        }
                        rwStringPack.save(String(byte))
                        return
                    })
                val result = indy_pack_message(
                    commandHandle,
                    walletHandle,
                    reference,
                    size,
                    recipientVkData,
                    senderVkData,
                    packMessageCb
                )
                sleep(6)
                println(result.toInt())
                if (result.toInt() != 0)
                    throw Exception("PackException")
                var stringResult = rwStringPack.read()
                return stringResult.toByteArray()
            }
        }

        actual fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = Api.atomicInteger.value++
            val authCrypCb: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<indy_u8_tVar>?, indy_u32_t) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    _: indy_error_t,
                    data: CPointer<indy_u8_tVar>?,
                    size: indy_u32_t
                ) {
                    initRuntimeIfNeeded()
                    val byte: ByteArray = ByteArray(size.toInt()).apply {
                        usePinned {
                            memcpy(it.addressOf(0), data, size.toULong())
                        }
                    }
                    rwStringUnPack.save(String(byte))
                    return
                })
            val result = indy_unpack_message(
                commandHandle,
                walletHandle,
                jwe_data as CValuesRef<indy_u8_tVar>,
                jwe_data.size as indy_u32_t,
                authCrypCb
            )
            sleep(8)
            if (result.toInt() != 0)
                throw Exception("UnPackException")
            var stringResult = rwStringUnPack.read()
            return stringResult.toByteArray()
        }
    }
}
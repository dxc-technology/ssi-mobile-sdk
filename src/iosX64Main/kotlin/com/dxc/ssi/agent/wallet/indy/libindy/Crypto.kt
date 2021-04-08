package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.cinterop.*
import platform.posix.sleep


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
                val byteArrray = string.toByteArray(Charsets.UTF_8)

                val walletHandle = wallet.getWalletHandle()
                val commandHandle = Api.atomicInteger.value++
                val size: UByteArray = message.toUByteArray()
                val byteArray = message
                val intArray = IntArray(byteArray.size)
                val res: CValuesRef<IntVar> = intArray.refTo(0)
                val uByteArray = message.toUByteArray()
                val def = message.toString()
                val s = message.size
                val us = uByteArray.size
                for ((i, arg) in message.withIndex()) {
                    println("${arg} ${uByteArray[i]}")
                }
                val packMessageCb: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<indy_u8_tVar>?, indy_u32_t) -> Unit>>? =
                    staticCFunction(fun(
                        _: indy_handle_t,
                        err: indy_error_t,
                        data: CPointer<indy_u8_tVar>?,
                        un: indy_u32_t
                    ) {
                        //initRuntimeIfNeeded()
                        return
                    })
                val result = indy_pack_message(
                    commandHandle,
                    walletHandle,
                    byteArrray.toUByteArray().refTo(0) as CValuesRef<indy_u8_tVar /* = UByteVarOf<UByte> */>?,
                    byteArray.size.toUInt(),
                    recipientVk,
                    senderVk,
                    packMessageCb
                )
                //sleep(8)
                if (result.toInt() != 0)
                    throw Exception("PackException")
                return byteArray
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
                    unsigned: indy_u32_t
                ) {
                    //initRuntimeIfNeeded()
                    //return
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
            return ByteArray(0)
        }
    }
}
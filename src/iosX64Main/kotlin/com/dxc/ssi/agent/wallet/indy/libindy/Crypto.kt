package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.*
import kotlinx.cinterop.*
import platform.posix.sleep

actual class Crypto {
    actual companion object {
        actual fun packMessage(
            wallet: Wallet,
            recipientVk: String,
            senderVk: String?,
            message: ByteArray
        ): ByteArray {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = Api.atomicInteger.value++
            val packMessageCb: CPointer<CFunction<(indy_handle_t, indy_error_t,CPointer<indy_u8_tVar>?, indy_u32_t ) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    _: indy_error_t,
                    data: CPointer<indy_u8_tVar>?,
                    unsigned: indy_u32_t
                ) {
                    initRuntimeIfNeeded()
                    val data: String? = data.toString()
                    return
                })
            val result = indy_pack_message(
                commandHandle,
                walletHandle,
                message as CValuesRef<indy_u8_tVar>,
                message.size as indy_u32_t,
                recipientVk,
                senderVk,
                packMessageCb
            )
            sleep(8)
            if (result.toInt() != 0 || readwriter.read() != null)
                throw Exception("PackException")
            return ByteArray(0)
        }
        actual fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = Api.atomicInteger.value++
            val authCrypCb: CPointer<CFunction<(indy_handle_t, indy_error_t,CPointer<indy_u8_tVar>?, indy_u32_t ) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    _: indy_error_t,
                    data: CPointer<indy_u8_tVar>?,
                    unsigned: indy_u32_t
                ) {
                    initRuntimeIfNeeded()
                    val data: String? = data.toString()
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
            if (result.toInt() != 0 || readwriter.read() != null)
                throw Exception("UnPackException")
            return ByteArray(0)
        }
    }
}
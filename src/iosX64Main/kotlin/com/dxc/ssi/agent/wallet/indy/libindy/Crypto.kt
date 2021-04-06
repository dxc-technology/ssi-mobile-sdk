package com.dxc.ssi.agent.wallet.indy.libindy

import com.indylib.*
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValuesRef

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
            val packMessageCb: Any? = null
            val result = indy_pack_message(
                commandHandle,
                walletHandle,
                message  as CValuesRef<indy_u8_tVar>,
                10000,
                recipientVk,
                senderVk,
                packMessageCb as CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<indy_u8_tVar>?, indy_u32_t) -> Unit>>?
            )
            return ByteArray(0)
        }
        actual fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = Api.atomicInteger.value++
            val authCrypCb = null
            val result = indy_unpack_message(
                commandHandle,
                walletHandle,
                jwe_data as CValuesRef<indy_u8_tVar>,
                10000,
                authCrypCb
            )
            return ByteArray(0)
        }
    }
}
package com.dxc.ssi.agent.wallet.indy.libindy

actual class Crypto {
    actual companion object {
        actual fun packMessage(
            wallet: Wallet,
            recipientVk: String,
            senderVk: String?,
            message: ByteArray
        ): ByteArray {
            TODO("Not yet implemented")
        }
        actual fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {
            TODO("Not yet implemented")
        }
    }
}
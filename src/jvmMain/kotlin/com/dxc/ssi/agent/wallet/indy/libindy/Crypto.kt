package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.crypto.Crypto

actual class Crypto {
    actual companion object {
        actual suspend fun packMessage(wallet: Wallet, recipientVk: String, senderVk: String?, message: ByteArray): ByteArray {
            return Crypto.packMessage(wallet, recipientVk, senderVk, message).get()
        }

        actual suspend fun unpackMessage(wallet: Wallet, jwe_data: ByteArray ): ByteArray {
            return Crypto.unpackMessage(wallet, jwe_data).get()
        }
    }

}
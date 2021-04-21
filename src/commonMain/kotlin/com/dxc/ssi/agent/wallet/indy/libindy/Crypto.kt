package com.dxc.ssi.agent.wallet.indy.libindy

expect class Crypto {
    companion object {
        suspend fun packMessage(wallet: Wallet, recipientVk: String, senderVk: String?, message: ByteArray): ByteArray
        suspend fun unpackMessage(wallet: Wallet, jwe_data: ByteArray): ByteArray
    }
}
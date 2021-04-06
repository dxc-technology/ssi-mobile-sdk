package com.dxc.ssi.agent.wallet.indy.libindy

expect class Crypto {
    companion object {
        fun packMessage(wallet: Wallet, recipientVk: String, senderVk: String?, message: ByteArray): ByteArray
        fun unpackMessage(wallet: Wallet, jwe_data: ByteArray): ByteArray
    }
}
package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.indylib.*
import kotlinx.cinterop.*



actual class Crypto {

    data class PackedMessageCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val packedMessage: ByteArray
    ) : CallbackData

    data class UnPackedMessageCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val unPackedMessage: ByteArray
    ) : CallbackData

    actual companion object {

        private val logger: Kermit = Kermit(LogcatLogger())

        //@ExperimentalUnsignedTypes
        @OptIn(ExperimentalUnsignedTypes::class)
        actual suspend fun packMessage(
            wallet: Wallet,
            recipientVk: String,
            senderVk: String?,
            message: ByteArray
        ): ByteArray {
            //TODO: do we really need memScoped here???
            memScoped {


                val uByteMessage = message.toUByteArray()


                val cMessage = uByteMessage.refTo(0)
                val messageLen = uByteMessage.size.toUInt()

                val commandHandle = callbackHandler.prepareCallback()

                val callback =
                    staticCFunction { commandHandle: Int, errorCode: UInt, outMessage: CPointer<UByteVarOf<UByte>>?, packedMessageLen: UInt
                        ->
                        initRuntimeIfNeeded()
                        //TODO: check if it is okay to get out message using readBytes
                        val packedBytes = outMessage!!.readBytes(packedMessageLen.toInt())
                        callbackHandler.setCallbackResult(PackedMessageCallbackResult(commandHandle,errorCode,packedBytes))
                    }


                logger.log(Severity.Debug,"",null) { "Before calling indy_pack_message" }
                indy_pack_message(
                    commandHandle,
                    wallet.getWalletHandle(),
                    cMessage, messageLen, recipientVk, senderVk, callback
                )

                val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as PackedMessageCallbackResult
                return callbackResult.packedMessage

            }
        }


        actual suspend fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {
            val walletHandle = wallet.getWalletHandle()

            val uByteMessage = jwe_data.toUByteArray()


            val cMessage = uByteMessage.refTo(0)
            val messageLen = uByteMessage.size.toUInt()

            val commandHandle = callbackHandler.prepareCallback()

            val callback = staticCFunction{ commandHandle: Int, errorCode: UInt, outMessage: CPointer<UByteVarOf<UByte>>?, packedMessageLen: UInt
                ->
                initRuntimeIfNeeded()

                //TODO: check if it is okay to get out message using readBytes
                val unPackedBytes = outMessage!!.readBytes(packedMessageLen.toInt())
                callbackHandler.setCallbackResult(UnPackedMessageCallbackResult(commandHandle,errorCode,unPackedBytes))

            }

            indy_unpack_message(
                commandHandle,
                walletHandle,
                cMessage,
                messageLen,
                callback
            )

            val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as UnPackedMessageCallbackResult
            return callbackResult.unPackedMessage
        }
    }
}
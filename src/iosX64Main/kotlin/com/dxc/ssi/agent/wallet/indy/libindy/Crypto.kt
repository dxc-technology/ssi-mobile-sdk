package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.transport.Sleeper
import com.indylib.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.cinterop.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicInt
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

class Workaround() {

    val callbackCompleted = AtomicReference<Boolean>(false)
    val atomicInt = AtomicInt(0)
    val atomicString = AtomicReference<String>("")
    val atomicByteArray = AtomicReference<ByteArray>(ByteArray(10).freeze())

    fun setIntValue(intValue: Int) {
        this.atomicInt.value = intValue
    }

    fun getIntValue(): Int {
        return atomicInt.value
    }

    fun setStringValue(s: String) {
        atomicString.value = s
    }

    fun getStringValue(): String {
        return atomicString.value
    }

    fun resetCallbackStatus() {
        callbackCompleted.value = false
    }

    fun setCallbackCompleted() {
        callbackCompleted.value = true
    }

    fun isCallbackCompleted(): Boolean {
        return callbackCompleted.value
    }

    fun setByteArray( byteArray: ByteArray) {
        atomicByteArray.value = byteArray
    }

    fun getByteArray( ): ByteArray {
        return atomicByteArray.value!!
        //TODO()
    }
}

@SharedImmutable
val workaround = Workaround()


actual class Crypto {
    actual companion object {

        //@ExperimentalUnsignedTypes
        @OptIn(ExperimentalUnsignedTypes::class)
        actual fun packMessage(
            wallet: Wallet,
            recipientVk: String,
            senderVk: String?,
            message: ByteArray
        ): ByteArray {

            memScoped {

                // enableIndyLog()

                val thisCommandHandle = 1111

                val uByteMessage = message.toUByteArray()


                val cMessage = uByteMessage.refTo(0)
                val messageLen = uByteMessage.size.toUInt()

                println("UnPacked message len = $messageLen")

                workaround.resetCallbackStatus()
                println("Before defining callback")

                val callback =
                    staticCFunction { commandHandle: Int, errorCode: UInt, outMessage: CPointer<UByteVarOf<UByte>>?, packedMessageLen: UInt
                        ->
                        initRuntimeIfNeeded()
                        println("Executing callback")
                        val strCommandHandle = commandHandle.toString()
                        println("Executing callback from create_wallet: commandHandle = $strCommandHandle, errorCode = $errorCode")

                        println("Packed message len = $packedMessageLen")

                        val packedBytes = outMessage!!.readBytes(packedMessageLen.toInt())
                        //val cell = outMessage!!.

                        //TODO: check if it is okay to get out message using readBytes

                        println("Packed bytes = $packedBytes")

                        workaround.setByteArray(packedBytes.freeze())
                        println("Callback: set byteArray")
                        workaround.setIntValue(commandHandle)
                        println("Callback: set int val")
                        workaround.setStringValue(strCommandHandle)
                        println("Callback: set string val")

                        workaround.setCallbackCompleted()
                        println("Callback: set callback completed")

                    }


                println("Before calling indy_pack_message")
                indy_pack_message(
                    thisCommandHandle,
                    wallet.getWalletHandle(),
                    cMessage, messageLen, recipientVk, senderVk, callback
                )
                println("After calling indy_pack_message")
                waitForCallback(workaround)

            }

           return workaround.getByteArray()

        }

        private fun enableIndyLog() {
            val context: CValuesRef<*>? = null
            val enabledFn: CPointer<CFunction<(
                COpaquePointer?, indy_u32_t,
                CPointer<ByteVar>?
            ) -> indy_bool_t>>? = null
            val flushFn: CPointer<CFunction<(COpaquePointer?) -> Unit>>? = null
            val myExitCallback = staticCFunction(fun(
                log: CPointer<out CPointed>?,
                elem: indy_u32_t,
                pointer: CPointer<ByteVar>?,
                val1: CPointer<ByteVar>?,
                val2: CPointer<ByteVar>?,
                val3: CPointer<ByteVar>?,
                number: indy_u32_t,
            ) {
                initRuntimeIfNeeded()
                println(pointer?.toKString())
                println(val1?.toKString())
                println(val2?.toKString())
                println(val3?.toKString())
                return
            })
            indy_set_logger(
                context,
                enabledFn,
                myExitCallback,
                flushFn
            )
            sleep(6)
        }

        private fun waitForCallback(workaround: Workaround): Int {
            //TODO: instead of sleep in the loop find out some proper kotlin solution, like channels, coroutines.
            //TODO: introduce timeout which is randomly increases
            //TODO: introduce some handling of command handle inside of this fun or some helper class
            while (!workaround.isCallbackCompleted()) {
                println("Callback is not completed. Sleeping...")
                Sleeper().sleep(2000)
            }
            return workaround.getIntValue()

        }


        actual fun unpackMessage(
            wallet: Wallet,
            jwe_data: ByteArray
        ): ByteArray {

            TODO("Not implemented")
        }
    }
}
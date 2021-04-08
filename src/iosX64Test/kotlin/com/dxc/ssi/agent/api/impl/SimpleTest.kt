package com.dxc.ssi.agent.api.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.DidDocument
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import com.dxc.ssi.agent.wallet.indy.libindy.Api
import com.dxc.ssi.agent.wallet.indy.libindy.Crypto
import com.indylib.*
import io.ktor.util.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.cinterop.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import platform.Foundation.*
import platform.posix.sleep
import kotlin.native.concurrent.AtomicReference
import kotlin.test.Test

@SharedImmutable
val rw = ReadWrite()

class ReadWrite {
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
typealias MyCallbackWallet = CPointer<CFunction<(indy_handle_t, indy_error_t) -> Unit>>?
typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>
typealias MyCallbackWallet2 = CPointer<CFunction<(indy_handle_t, indy_error_t, indy_handle_t) -> Unit>>

class IosIndyTest {

    @Test
    fun test_indy_log() {

        memScoped {
            val context: CValuesRef<*>? = null
            val enabledFn2: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) -> indy_bool_t /* = Boolean */>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?) → indy_bool_t /* = Boolean */>>> */>? =
                null
            val logFn: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */, indy_u32_t /* = UInt */, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, CPointer<ByteVar /* = ByteVarOf<Byte> */>?, indy_u32_t /* = UInt */) → Unit>>> */>? =
                null
            val flushFn2: CValuesRef<CPointerVar<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) -> Unit>> /* = CPointerVarOf<CPointer<CFunction<(COpaquePointer? /* = CPointer<out CPointed>? */) → Unit>>> */>? =
                null
            indy_get_logger(
                context,
                enabledFn2,
                logFn,
                flushFn2
            )
            sleep(10)
            val command = 1
            val pointer = "132"
            val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
            val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
            val myExit_cb: MyCallbackWallet = staticCFunction(fun(
                xcommand_handle: indy_handle_t,
                err: indy_error_t,
            ) {
                return
            })
            indy_create_wallet(
                command,
                config,
                credentials,
                myExit_cb
            )
            sleep(10)
        }
    }

    @Test
    fun run() {
        val didJson = "{}"
        val commandHandle = 0
        val walletHandle = 0
        val callback: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println(did?.toKString())
            println(verkey?.toKString())
            println(xcommand_handle)
            println(err)
            return
        })
        val exitCode: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            walletHandle,
            didJson,
            callback
        )
        println(exitCode)
        sleep(5)
    }

    @Test
    fun test_indy_create_wallet() {

        val command = 1
        val pointer = "167"
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
            return
        })
        indy_set_logger(
            context,
            enabledFn,
            myExitCallback,
            flushFn
        )
        sleep(8)
        val config = "{\"id\":\"testWalletName${pointer}\",\"storage_type\":\"default\"}"
        val credentials = "{\"key\":\"testWalletPassword${pointer}\"}"
        val myExit_cb: MyCallbackWallet = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
        ) {
            initRuntimeIfNeeded()
            return
        })
        indy_create_wallet(
            command,
            config,
            credentials,
            myExit_cb
        )
        sleep(8)
        val myExit_cb2: MyCallbackWallet2 = staticCFunction(fun(
            command: indy_handle_t,
            err: indy_error_t,
            handle: indy_handle_t
        ) {
            initRuntimeIfNeeded()
            return
        })
        indy_open_wallet(
            command,
            config,
            credentials,
            myExit_cb2
        )

        sleep(8)
        val didJson = "{}"
        val commandHandle = 1
        val wallethandle = 3
        memScoped {
            val callback: MyCallback = staticCFunction(fun(
                xcommand_handle: indy_handle_t,
                err: indy_error_t,
                did: CPointer<ByteVar>?,
                verkey: CPointer<ByteVar>?
            ) {
                initRuntimeIfNeeded()
                val didData: String? = did?.toKString()
                val verkeyData: String? = verkey?.toKString()
                println("Print:Did:${didData} VerKey:${verkeyData}")
                rw.save("Did:${didData} VerKey:${verkeyData}")
                return
            })
            indy_create_and_store_my_did(
                commandHandle,
                wallethandle,
                didJson,
                callback
            )
            sleep(8)
            println(rw.read())
        }
    }

    @Test
    fun test_indy_create_and_store_my_did() {

        val commandHandle = 1
        val walletHandle = 3
        val didJson = "{}"
        val myExit_cb2: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println("did")
            println(xcommand_handle)
            println(err)
            println(did?.toKString())
            println(verkey?.toKString())
            println(did!!.getRawValue())
            println(interpretCPointer<ByteVar>(did.getRawValue())?.toKString())
            return
        })
        val result2: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            walletHandle,
            didJson,
            myExit_cb2
        )
        assert(result2.toInt().equals(0))
        println(result2)
        sleep(8)
    }


    @ExperimentalUnsignedTypes
    @Test
    fun test_indy_array() {

        val data = "{\"@type\":\"https://didcomm.org/connections/1.0/request\",\"@id\":\"0036762e-62e2-45a8-9126-3646f04fccad\",\"label\":\"Holder\",\"imageUrl\":null,\"connection\":{\"DID\":\"LcNB1rQdukkvT77K9txa9t\",\"DIDDoc\":{\"@context\":\"https://w3id.org/did/v1\",\"id\":\"LcNB1rQdukkvT77K9txa9t\",\"publicKey\":[{\"id\":\"LcNB1rQdukkvT77K9txa9t\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"LcNB1rQdukkvT77K9txa9t\",\"publicKeyBase58\":\"BguMNjqh9QLRdozPXTwvRWzNe9eSQK4P1xX8BHGVG5LG\"}],\"service\":[{\"id\":\"LcNB1rQdukkvT77K9txa9t;indy\",\"type\":\"IndyAgent\",\"priority\":0,\"recipientKeys\":[\"BguMNjqh9QLRdozPXTwvRWzNe9eSQK4P1xX8BHGVG5LG\"],\"serviceEndpoint\":\"ws://test85597:8123\"}]}}}"
        val byteArrray = data.toByteArray()
        val recipientVk = "[\"AVGGq5RRPkF7vMqD5niJzbr5y9cwWdGGPYfygJso13GT\"]"
        val senderVk = "BguMNjqh9QLRdozPXTwvRWzNe9eSQK4P1xX8BHGVG5LG"
        val walletHandle = 4
        val commandHandle = 3

        memScoped {
            //{"protected":"eyJlbmMiOiJ4Y2hhY2hhMjBwb2x5MTMwNV9pZXRmIiwidHlwIjoiSldNLzEuMCIsImFsZyI6IkFub25jcnlwdCIsInJlY2lwaWVudHMiOlt7ImVuY3J5cHRlZF9rZXkiOiJuQ3Z4VUlad01rOU5DNEw1SmNPYUwtODBJU1ZRcHRRUnkxTWg3ZTkzWjNxZ05ySFZmR0ExQm9zTWN2d3RtdXBlMHNEaC1LbnZXX2R2RHU2c2dsMUpINnZzNVF4OXhYMVA3ejVhcl9iYkFHdz0iLCJoZWFkZXIiOnsia2lkIjoiSDZhajRWREZLZTFUN01COU1qcGlaUDU3Q1ZaMm1XNkxrb0JFTW41ZGM0QkUifX1dfQ==","iv":"Ld2YxKTXQPV2gPMe","ciphertext":"Z0rjwlbBV1udc-fu0FxFz9wnRK383IuHlOC8tv7_pMMghBtEKwf0YtWh2e_ZWqpFHKiNwEqzBfhUFV17iVrJDyIOM7NTidzwd_wRilchmo-vqEG4T9DdhMsKvdhUP2PfSdQSxDb0Qd39GCVL6OW2ChM0rkE9Yuxh-OFC2DTB0gZUDdWdg69XTVBkL8BKtPu0n5-zAGgmqgaFmP8p92iG3iZS5oIlGc3B_wUink2l0GcVg4VDz-PZq7NLIJDLtDxX7vQ7P-4JAp_YODeACLgM03UjHQVXXzdL4-A7Vynx4zgB-oIxVnUhUeV6q6j6VnB4rBEpPOxXOHdPbn7DyNCzyI3zBkOn4vmlbCWCLReKF_TINbj8IFobJPht8kqlh_17SvDQrPNkALrTOGTT_dZgIiw41AtcMkYYfFMb6QQcS39VTYKK_6N1N81EZnSBydEPke0bgLoA93W9KWYPt1xLvxyvG8YDizXQdoFWXk99nfmVilhG3pzoDPCkyLpGR9kfsi3FlMzqIr_d9cZN7QJ_HPfIgTS1eeZAYLFeVibD12u4ZQIdLZMJeI07mqc5tKnwkzXWUY_FNqHmVlB7CGCo4MEG-wBIUcF-KLh_0lm4sSNzjTID2rc-k_gimhwPUMBm59HPcWRTUYLrpMAlOy3f6DoS5_4Sy2O4ddKSw611lyvV4_AIuWFjoeTN9StCPxYob0DuM6MqZsNNFVQNdUTYXwk9mw6kM9x9Q5lBnf684UdRFjGr4NyhJDdinYEeh0c5MEesDW_P9eJ6wL9U1tHLDXScOSFCCIbdmXVyioOc6skieKVJz15CPVCOXZRGxYGB25WRua7tSGVTXNXcIMs2xA_wz4Xdh8c-7OxC4akpecOgcen5hb_f2XxktyP-rcN4r2G0QuLh-MT3yiAP-I8vnwWWoiqemg2vYUU_cadocJwUl-9N4if1R_20_oFCMBELABRRzAmRcWfoJyH4WajoCHVa-IfMfJAe3Q9ZrhFUD4biVVbJuky3S_bBjp6YBQeti6hAUBXc6W_rZorxn7Fp-U93GDRrZwqhUrG1zzjibW2nbnfm7jYqmeOe6tAH2Q10db2YJDxM0pCspRohSI_vC4lnszsURMIoswPVwTDuBR2GTbQptaZlJ8ZH98GtlS9V2ArHH6A2UZ-UIXf2shhj745c7YvNLGRvMl7dSb8gpC1hvDMb8GjwhlqPzrb0X2xkY3OrbbVs0hJ1JQ5tEQmbvCS02nUn6HR0-RYxM4R3UCNDEaFxd5oXU1Xfv1-OmpHLXClbBSz3TFk-2eLUsipg9xXt9V3WNfLK5PPxXgjorNnv7rZK13sYSMxj3crGIFCbBSZfwPzZqVx-ZX7HPZLw_uFbZa5CK0W-rVb234tNcPS3nQZUu8mnWaw-E2_KuwcvW-rz8fLs1ywWdGaFXsm17LX6zZiyOGACzCOOvlCr-OuOOhPIE1DLMUyr47oT4YV1T16QTwgHnJlSLF53ddB0h_7fwO85Nc1rziX76MGfK236__o48NBIdCiJfTuUeA9wJG73QYGlefDVOi47x6wBcGM_tX744XjENI8tQ5XEEpb_NNlBiKzpmrvaAVDLAVwBYcmZAsph0vYOw4cGlfhTLeFDdEFEAHH5SWTVdpdiJ4mokHJmMQkCu4wIZ41wa9OX3vMpCosygW5tTqhcA9N7lPudyHsrrASXl6d73rRQsi5EhDT-eTuMhdJGI7Th-IvoHL9VvyCuB5UmBgijSJbks3hmbn90Nz0F4POt-d-nXaegakfSKEO9K2vxEYMFvGziweyiY3pgN2OzaTlw5NlGWmysAbuS-8K97LJI6IKFVqyvPGSZLiiIj4d5VVTIBGAxU2kk4RtKRXi9SemQhQQai4FbkQ8mz7LJHbtkFpHuUi5y24PXT3xl53fqqlI3pa1dug5UHZNFHYQIScTf2gTt1QmnG-4iJYmrL7RioIEoUa7BdRHurMgFElqZ__QKq9PEDDoc3z-kItBPAe_a4s1YbE_WpvvbO7QXge0tmbApuqAI4-68ZDSYhoYjIxGeJY8DqtWsSTbOMCZhmnjTjbf2bvISzBFQnPi8S4OyYrxcVMKO0OPow_ZAarY=","tag":"3EoGg7faQj1atxoHqlGxqg=="}
            val packMessageCb: CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<indy_u8_tVar>?, indy_u32_t) -> Unit>>? =
                staticCFunction(fun(
                    _: indy_handle_t,
                    er: indy_error_t,
                    data: CPointer<indy_u8_tVar>?,
                    un: indy_u32_t
                ) {
                    initRuntimeIfNeeded()
                    //rw2.save(data as ByteArray)
                    var d = data?.readBytes(5)
                    println(d)
                    return
                })
            val result = indy_pack_message(
                commandHandle,
                walletHandle,
                byteArrray.toUByteArray().refTo(0) as CValuesRef<indy_u8_tVar /* = UByteVarOf<UByte> */>?,
                byteArrray.size.toUInt(),
                recipientVk,
                senderVk,
                packMessageCb
            )
            // rw2.read()
            sleep(8)
            if (result.toInt() != 0)
                throw Exception("PackException")
        }
    }
}




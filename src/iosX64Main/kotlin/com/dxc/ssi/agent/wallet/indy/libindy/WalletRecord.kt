package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.indylib.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.sleep


actual class WalletRecord {
    data class GetWalletRecordCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val walletRecord: String?
        ) : CallbackData
    //TODO: create generic data class for cases when we do not need any customization
    data class AddWalletRecordCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt
        ) : CallbackData
    //TODO: create generic data class for cases when we do not need any customization
    data class UpdateWalletRecordCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt
    ) : CallbackData

    actual companion object {
        actual suspend fun get(
            wallet: Wallet,
            type: String,
            id: String,
            optionsJson: String
        ): String {
            println("Entered WalletRecord.get")
            //TODO: check if we need memScoped here and everywhere
            memScoped {
                val walletHandle = wallet.getWalletHandle()
                val commandHandle = callbackHandler.prepareCallback()

                val callback =
                staticCFunction() { commandHandle: Int, errorCode: UInt, walletRecordData: CPointer<ByteVar>?
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        GetWalletRecordCallbackResult(
                            commandHandle, errorCode,
                            walletRecordData?.toKString(),

                        )
                    )
                }

                val result: indy_error_t = indy_get_wallet_record(
                    commandHandle,
                    walletHandle,
                    type,
                    id,
                    optionsJson,
                    callback
                )

                val recordData = callbackHandler.waitForCallbackResult(commandHandle) as GetWalletRecordCallbackResult


                return recordData.walletRecord!!
            }
        }

        actual suspend fun add(
            wallet: Wallet,
            type: String,
            id: String,
            value: String,
            tagsJson: String?
        ) {

            val commandHandle = callbackHandler.prepareCallback()
            val callback =
                staticCFunction() { commandHandle: Int, errorCode: UInt
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        AddWalletRecordCallbackResult(
                            commandHandle, errorCode)
                    )
                }

            val walletHandle = wallet.getWalletHandle()

            indy_add_wallet_record(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                tagsJson,
                callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)
        }

        actual suspend fun updateValue(
            wallet: Wallet,
            type: String,
            id: String,
            value: String
        ) {
            val walletHandle = wallet.getWalletHandle()
            val commandHandle = callbackHandler.prepareCallback()
            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        UpdateWalletRecordCallbackResult(
                            commandHandle, errorCode)
                    )
                }

            indy_update_wallet_record_value(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)
        }

        actual suspend fun updateTags(
            wallet: Wallet,
            type: String,
            id: String,
            tagsJson: String
        ) {
        }

        actual suspend fun delete(
            wallet: Wallet,
            type: String,
            id: String
        ) {
        }
    }
}
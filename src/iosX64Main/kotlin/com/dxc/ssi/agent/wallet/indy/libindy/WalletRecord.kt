package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.indylib.*
import kotlinx.cinterop.*


actual class WalletRecord {
    data class GetWalletRecordCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val walletRecord: String?
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

                indy_get_wallet_record(
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
            println("Adding walletRecord: type=$type, id = $id, value = $value, tagsJson = $tagsJson")

            val commandHandle = callbackHandler.prepareCallback()

            val walletHandle = wallet.getWalletHandle()

            indy_add_wallet_record(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                tagsJson,
                SimpleCallback.callback
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

            indy_update_wallet_record_value(
                commandHandle,
                walletHandle,
                type,
                id,
                value,
                SimpleCallback.callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)
        }

        actual suspend fun updateTags(
            wallet: Wallet,
            type: String,
            id: String,
            tagsJson: String
        ) {

            val commandHandle = callbackHandler.prepareCallback()

            indy_update_wallet_record_tags(
                commandHandle,
                wallet.getWalletHandle(),
                type,
                id,
                tagsJson,
                SimpleCallback.callback
            )

            callbackHandler.waitForCallbackResult(commandHandle)
        }

        actual suspend fun delete(
            wallet: Wallet,
            type: String,
            id: String
        ) {

            val commandHandle = callbackHandler.prepareCallback()

            indy_delete_wallet_record(
                commandHandle,
                wallet.getWalletHandle(),
                type,
                id,
                SimpleCallback.callback)

            callbackHandler.waitForCallbackResult(commandHandle)



        }
    }
}
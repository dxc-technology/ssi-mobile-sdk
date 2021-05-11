package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.*
import kotlinx.cinterop.*


actual class WalletRecord {

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

                indy_get_wallet_record(
                    commandHandle,
                    walletHandle,
                    type,
                    id,
                    optionsJson,
                    StringCallback.callback
                )

                val recordData = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result


                return recordData.stringResult
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
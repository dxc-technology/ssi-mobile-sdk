package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.exceptions.indy.WalletItemNotFoundException
import org.hyperledger.indy.sdk.non_secrets.WalletRecord
import java.util.concurrent.ExecutionException

actual class WalletRecord {
    actual companion object {
        actual suspend fun get(wallet: Wallet, type: String, id: String, optionsJson: String): String {
            try {
                return WalletRecord.get(wallet.wallet, type, id, optionsJson).get()
            } catch (e: ExecutionException) {
                if (e.cause is org.hyperledger.indy.sdk.wallet.WalletItemNotFoundException)
                    throw WalletItemNotFoundException()
                else throw e
            }

        }

        actual suspend fun add(wallet: Wallet, type: String, id: String, value: String, tagsJson: String?) {
            WalletRecord.add(wallet.wallet, type, id, value, tagsJson).get()
        }

        actual suspend fun updateValue(wallet: Wallet, type: String, id: String, value: String) {
            WalletRecord.updateValue(wallet.wallet, type, id, value).get()
        }

        actual suspend fun updateTags(wallet: Wallet, type: String, id: String, tagsJson: String) {
            WalletRecord.updateTags(wallet.wallet, type, id, tagsJson).get()
        }

        actual suspend fun delete(wallet: Wallet, type: String, id: String) {
            WalletRecord.delete(wallet.wallet, type, id).get()
        }
    }

}
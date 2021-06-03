package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletCreationStrategy
import com.dxc.ssi.agent.api.pluggable.wallet.WalletManager
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.utils.CoroutineHelper
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import com.dxc.ssi.agent.wallet.indy.libindy.CreateAndStoreMyDidResult
import com.dxc.ssi.agent.wallet.indy.libindy.Did
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class IndyWalletManager {
    companion object : WalletManager {
        override fun isWalletExistsAndOpenable(walletName: String, walletPassword: String): Boolean {
            val walletManagerScope = CoroutineScope(Dispatchers.Default)
            return CoroutineHelper.waitForCompletion(walletManagerScope.async {
                return@async try {
                    val wallet = WalletHelper.openExisting(walletName, walletPassword)
                    wallet.closeWallet()
                    //TODO: check if wallet needs to be closed here
                    true
                } catch (t: Throwable) {
                    false
                }
            })
        }

        override fun isDidExistsInWallet(did: String, walletName: String, walletPassword: String): Boolean {
            val walletManagerScope = CoroutineScope(Dispatchers.Default)
            val didWithMetaResult = CoroutineHelper.waitForCompletion(walletManagerScope.async {
                val wallet = WalletHelper.openExisting(walletName, walletPassword)
                val didWithMetadata = Did.getDidWithMeta(wallet, did)
                wallet.closeWallet()
                didWithMetadata
            })

            return didWithMetaResult.did == did

        }

        override fun createWallet(
            walletName: String,
            walletPassword: String,
            walletCreationStrategy: WalletCreationStrategy
        ) {
            val walletManagerScope = CoroutineScope(Dispatchers.Default)

            CoroutineHelper.waitForCompletion(walletManagerScope.async {
                when (walletCreationStrategy) {
                    WalletCreationStrategy.TruncateAndCreate -> {
                        WalletHelper.createOrTrunc(walletName, walletPassword)
                    }
                    WalletCreationStrategy.CreateOrFail -> {
                        WalletHelper.createOrFail(walletName, walletPassword)
                    }
                }
            })
        }

        //TODO: add option to create new did and return it
        override fun createDid(
            didConfig: DidConfig,
            walletName: String,
            walletPassword: String
        ): CreateAndStoreMyDidResult {
            val walletManagerScope = CoroutineScope(Dispatchers.Default)

            val didConfigJson = Json.encodeToString(didConfig)

            return CoroutineHelper.waitForCompletion(walletManagerScope.async {
                val wallet = WalletHelper.openExisting(walletName, walletPassword)
                val didResult = Did.createAndStoreMyDid(wallet, didConfigJson)
                wallet.closeWallet()
                didResult
            })

        }
    }
}
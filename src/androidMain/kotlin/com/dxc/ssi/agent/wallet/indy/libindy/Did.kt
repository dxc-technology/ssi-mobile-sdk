package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.did.Did

actual class Did {
    actual companion object {
        actual suspend fun createAndStoreMyDid(
            wallet: Wallet,
            didJson: String
        ): CreateAndStoreMyDidResult {
            return Did.createAndStoreMyDid(wallet.wallet, didJson).get()
        }

        actual suspend fun getDidWithMeta(
            wallet: Wallet,
            did: String
        ): DidWithMetadataResult {
            TODO("Not yet implemented")
        }

    }

}
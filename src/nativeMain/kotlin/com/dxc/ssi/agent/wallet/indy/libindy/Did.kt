package com.dxc.ssi.agent.wallet.indy.libindy

actual class Did {
    actual companion object {
        actual suspend fun createAndStoreMyDid(
            wallet: Wallet,
            didJson: String
        ): CreateAndStoreMyDidResult {
            TODO("Not yet implemented")
        }

        actual suspend fun getDidWithMeta(
            wallet: Wallet,
            did: String
        ): DidWithMetadataResult {
            TODO("Not yet implemented")
        }

    }

}
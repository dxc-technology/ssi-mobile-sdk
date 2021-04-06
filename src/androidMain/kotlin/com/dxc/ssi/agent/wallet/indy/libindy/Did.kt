package com.dxc.ssi.agent.wallet.indy.libindy

import org.hyperledger.indy.sdk.did.Did

actual class Did {
    actual companion object {
        actual fun createAndStoreMyDid(
            wallet: Wallet,
            didJson: String
        ): CreateAndStoreMyDidResult {
            return Did.createAndStoreMyDid(wallet, didJson).get()
        }

    }

}
package com.dxc.ssi.agent.wallet.indy.libindy

expect class Did {

    companion object {

        fun createAndStoreMyDid(wallet: Wallet, didJson: String): CreateAndStoreMyDidResult

    }

}
package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.IdentityDetails

actual open class IndyWalletHolder : WalletHolder {
    actual override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    actual override fun storeConnectionRecord(connection: Connection) {
        TODO("Not yet implemented")
    }

    actual override fun getConnectionRecordById(): Connection {
        TODO("Not yet implemented")
    }

    actual override fun openOrCreateWallet() {
    }
}
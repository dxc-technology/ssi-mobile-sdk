package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.model.IdentityDetails

actual class IndyProver : IndyWalletHolder(), Prover {
    actual override fun createMasterSecret(id: String) {
        TODO("Not yet implemented")
    }

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

}

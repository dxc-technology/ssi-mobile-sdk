package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.model.IdentityDetails

actual class IndyIssuer : IndyWalletHolder(), Issuer {
    actual override fun sign(data: String): String {
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

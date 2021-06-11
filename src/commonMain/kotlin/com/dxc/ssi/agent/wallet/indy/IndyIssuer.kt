package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.IdentityDetails

class IndyIssuer(walletHolder: WalletHolder) : Issuer {
    override fun sign(data: String): String {
        TODO("Not implemented")
    }

}

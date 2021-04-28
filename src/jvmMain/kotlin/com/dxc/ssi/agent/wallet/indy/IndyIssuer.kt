package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.IdentityDetails

actual class IndyIssuer actual constructor(private val walletHolder: WalletHolder) : Issuer {
    actual override fun sign(data: String): String {
        TODO("Not yet implemented")
    }
}

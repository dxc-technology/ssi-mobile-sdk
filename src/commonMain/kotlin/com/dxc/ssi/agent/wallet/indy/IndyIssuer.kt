package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.IdentityDetails

//TODO: consider going deeper and expect not our classes but underlying library instead. I.e. make this class common  but implement Wallet, WalletRecord etc, classes from the library
expect class IndyIssuer(walletHolder: WalletHolder) : Issuer {
    override fun sign(data: String): String

}

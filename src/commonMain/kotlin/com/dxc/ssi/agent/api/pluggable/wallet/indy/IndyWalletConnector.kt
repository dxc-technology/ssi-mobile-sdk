package com.dxc.ssi.agent.api.pluggable.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.wallet.indy.*
//TODO: think if this class is needed and change it to be not that ugly
object IndyWalletConnector {

    fun build(
        walletHolder: WalletHolder
    ): WalletConnector {

        return WalletConnector(
            issuer = IndyIssuer(walletHolder),
            prover = IndyProver(walletHolder),
            verifier = IndyVerifier(walletHolder),
            trustee = IndyTrustee(walletHolder),
            walletHolder = walletHolder
        )
    }
}
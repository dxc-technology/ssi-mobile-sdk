package com.dxc.ssi.agent.api.pluggable.wallet

data class WalletConnector(
    val issuer: Issuer?,
    val prover: Prover?,
    val verifier: Verifier?,
    val trustee: Trustee?,
    val walletHolder: WalletHolder
)

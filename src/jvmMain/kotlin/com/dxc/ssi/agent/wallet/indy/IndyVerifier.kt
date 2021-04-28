package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Verifier
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder

actual class IndyVerifier actual constructor(private val walletHolder: WalletHolder) : Verifier {

}

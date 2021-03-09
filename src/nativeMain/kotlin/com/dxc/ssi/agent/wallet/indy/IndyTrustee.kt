package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Trustee
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.IdentityDetails

actual class IndyTrustee actual constructor(walletHolder: WalletHolder) :IndyWalletHolder(), Trustee {


}

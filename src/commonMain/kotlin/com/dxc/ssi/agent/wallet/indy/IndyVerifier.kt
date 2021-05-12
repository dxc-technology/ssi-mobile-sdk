package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Verifier
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder

//TODO: consider going deeper and expect not our classes but underlying library instead. I.e. make this class common  but implement Wallet, WalletRecord etc, classes from the library
class IndyVerifier(walletHolder: WalletHolder) : Verifier {

}

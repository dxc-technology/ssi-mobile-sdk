package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Verifier
import com.dxc.ssi.agent.model.messages.Message

actual class IndyVerifier :IndyWalletHolder(), Verifier {
    override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        TODO("Not yet implemented")
    }

}

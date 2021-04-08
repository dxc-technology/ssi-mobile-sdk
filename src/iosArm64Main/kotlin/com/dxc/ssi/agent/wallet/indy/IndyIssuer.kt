package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message

actual class IndyIssuer : IndyWalletHolder(), Issuer {
    actual override fun sign(data: String): String {
        TODO("Not yet implemented")
    }

    override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        TODO("Not yet implemented")
    }


}

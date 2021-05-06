package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message

actual class IndyProver : IndyWalletHolder(), Prover {
    actual override fun createMasterSecret(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        TODO("Not yet implemented")
    }


}

package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Issuer
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message

actual class IndyIssuer actual constructor(walletHolder: WalletHolder) : IndyWalletHolder(), Issuer {
    actual override  fun sign(data: String): String {
        TODO("Not yet implemented")
    }

    actual override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getConnectionRecordById(connectionId: String): Connection? {
        TODO("Not yet implemented")
    }

    override suspend fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        TODO("Not yet implemented")
    }

    override suspend fun unPackMessage(packedMessage: Message): Message {
        TODO("Not yet implemented")
    }

}

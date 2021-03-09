package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message

//TODO: consider going deeper and expect not our classes but underlying library instead. I.e. make this class common  but implement Wallet, WalletRecord etc, classes from the library
expect open class IndyWalletHolder(): WalletHolder {

    override fun createSessionDid(identityRecord: IdentityDetails): String

    override fun getIdentityDetails(): IdentityDetails

    override fun getIdentityDetails(did: String): IdentityDetails

    override fun getTailsPath(): String

    override fun storeConnectionRecord(connection: Connection)

    override fun getConnectionRecordById(connectionId: String): Connection?

    override fun findConnectionByVerKey(verKey: String): Connection?

    override fun openOrCreateWallet()

    override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean) : String

    override fun unPackMessage(packedMessage: Message) : Message



}
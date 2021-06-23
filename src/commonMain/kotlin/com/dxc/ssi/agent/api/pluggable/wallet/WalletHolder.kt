package com.dxc.ssi.agent.api.pluggable.wallet

import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.model.messages.Message

/**
 * Represents basic entity which has a wallet
 */
interface WalletHolder {

    fun createSessionDid(identityRecord: IdentityDetails): String

    fun getIdentityDetails(): IdentityDetails

    fun getIdentityDetails(did: String): IdentityDetails

   // fun getCredentials(): Iterator<CredentialReference>

    fun getTailsPath(): String

    suspend fun storeConnectionRecord(connection: PeerConnection)

    suspend fun getConnectionRecordById(connectionId: String): PeerConnection?

    suspend fun findConnectionByVerKey(verKey: String): PeerConnection?

    suspend fun getConnections(connectionState: PeerConnectionState?): Set<PeerConnection>

    //TODO: rewrite this to return Wallet object instead of Any
    fun getWallet(): Any

    //TODO: Support both Authcrypt and Anoncrypt modes
    suspend fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean = false) : String

    //TODO: Support both Authcrypt and Anoncrypt modes
    suspend fun unPackMessage(packedMessage: Message) : Message

    suspend fun openWalletOrFail()

    suspend fun isWalletHolderInitialized(): Boolean

    suspend fun initializeDid()

}
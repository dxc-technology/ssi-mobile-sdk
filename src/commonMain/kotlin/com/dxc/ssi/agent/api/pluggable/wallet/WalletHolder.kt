package com.dxc.ssi.agent.api.pluggable.wallet

import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message

/**
 * Represents basic entity which has a wallet
 */
interface WalletHolder {

    /**
     * Creates temporary did which can be used by identity to perform some any operations
     *
     * @param identityRecord [IdentityDetails] - identity details
     *
     * @return [String] - newly created did
     */
    fun createSessionDid(identityRecord: IdentityDetails): String

    /**
     * Gets [IdentityDetails] of current user
     *
     * @return [IdentityDetails]
     */
    fun getIdentityDetails(): IdentityDetails

    /**
     * Gets [IdentityDetails] of some did
     *
     * @param did [String]
     *
     * @return [IdentityDetails]
     */
    fun getIdentityDetails(did: String): IdentityDetails

    //TODO: return credentials only for current DID?
    /**
     * Gets Iterator [CredentialReference] in this wallet
     *
     * @return Iterator<[CredentialReference]>
     */
   // fun getCredentials(): Iterator<CredentialReference>

    /**
     * Gets TAILS file path
     *
     * @return TAILS file path
     */
    fun getTailsPath(): String

    /**
     * Stores Connection object in Non Secret space of wallet

     */
    suspend fun storeConnectionRecord(connection: Connection)

    /**
     * Gets connection record from wallet by id
     *
     * @return Connection object
     */
    suspend fun getConnectionRecordById(connectionId: String): Connection?

    /**
     * Find connection record in a wallet by verkey
     *
     * @return Connection object
     */
    fun findConnectionByVerKey(verKey: String): Connection?

    /**
     * Opens or creates wallet
     *
     * @return Connection object
     */
    suspend fun openOrCreateWallet()

    /**
     * Gets open wallet
     *
     * @return Any containing wallet
     */
    //TODO: rewrite this to return Wallet object instead of Any
    fun getWallet(): Any

    /**
     * Packs message
     *
     * @return Connection object
     */
    //TODO: Support both Authcrypt and Anoncrypt modes
    suspend fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean = false) : String

    /**
     * Unpacks message
     *
     * @return Connection object
     */
    //TODO: Support both Authcrypt and Anoncrypt modes
    suspend fun unPackMessage(packedMessage: Message) : Message


}
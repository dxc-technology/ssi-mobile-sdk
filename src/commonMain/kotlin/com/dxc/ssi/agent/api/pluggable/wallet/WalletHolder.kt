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
    fun storeConnectionRecord(connection: Connection)

    /**
     * Gets connection record from wallet by id
     *
     * @return Connection object
     */
    fun getConnectionRecordById(connectionId: String): Connection?

    /**
     * Gets connection record from wallet by id
     *
     * @return Connection object
     */
    fun openOrCreateWallet():Int

    /**
     * Packs message
     *
     * @return Connection object
     */
    //TODO: Support both Authcrypt and Anoncrypt modes
    fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean = false) : String

    /**
     * Unpacks message
     *
     * @return Connection object
     */
    //TODO: Support both Authcrypt and Anoncrypt modes
    fun unPackMessage(packedMessage: Message) : Message


}
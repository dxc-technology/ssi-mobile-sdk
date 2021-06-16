package com.dxc.ssi.agent.api.pluggable.wallet

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.common.SharedData
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Presentation
import com.dxc.ssi.agent.didcomm.model.verify.data.PresentationRequest
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.wallet.indy.model.revoke.RevocationState
import com.dxc.ssi.agent.wallet.indy.model.verify.RevocationRegistryEntry

/**
 * This entity is able to receive credentials and create proofs about them.
 * Has read-only access to public ledger.
 */
interface Prover {

    /**
     * Creates credential request
     *
     * @param proverDid [String] - prover's did
     * @param credentialDefinition [CredentialDefinition]
     * @param offer [CredentialOfferContainer] - credential offer
     * @param masterSecretId [String]
     *
     * @return [CredentialRequestInfo] - credential request and all reliable data
     */
    suspend fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        credentialOffer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo

    /**
     * Stores credential exchange record in a wallet
     *
     * @param credentialExchangeRecord [CredentialExchangeRecord] - credentialExchangeRecord to store
     */
    suspend fun storeCredentialExchangeRecord(credentialExchangeRecord: CredentialExchangeRecord)

    /**
     * Retrieves [CredentialExchangeRecord] from wallet
     *
     * @param thread [Thread]
     * @return [CredentialExchangeRecord?]
     */
    suspend fun getCredentialExchangeRecordByThread(thread: Thread): CredentialExchangeRecord?

    /**
     * Gets credential definition id from offer data
     *
     * @param offer [CredentialOfferContainer] - credential offer
     *
     * @return [CredentialDefinitionId] - id of credential definition
     */
    //TODO: temporarily this function in WalletConnector, but logically it is some other layer of abstraction.But for now we consider that wallet is aware about of technology(indy) specifics on how to parse message
    fun createCredentialDefinitionIdFromOffer(
        credentialOffer: CredentialOffer
    ): CredentialDefinitionId

    /**
     * Stores credential in prover's wallet
     *
     * @param credentialInfo [CredentialInfo] - credential and all reliable data
     * @param credentialRequest [CredentialRequestInfo] - credential request and all reliable data
     * @param offerContainer [CredentialOfferContainer] - credential offer
     * @param credentialDefinition [CredentialDefinition]
     * @param revocationRegistryDefinition [RevocationRegistryDefinition] on [null]
     *
     * @return local UUID of the stored credential in the prover's wallet
     */
    //TODO: fix javadoc above
    suspend fun receiveCredential(
        credential: Credential,
        credentialRequestInfo: CredentialRequestInfo,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String
    /**
     * Creates proof for provided proof request
     *
     * @param proofRequest [ProofRequest] - proof request created by verifier
     * @param extraQuery Map [String] to Map [String] to [Any] - additional WQL query applied to Wallet's credential search
     * @param provideSchema [SchemaProvider] - provide schema for each credential
     * @param provideCredentialDefinition [CredentialDefinitionProvider] - provide credential definition for each credential
     * @param masterSecretId [String]
     * @param revocationStateProvider [RevocationStateProvider] or [null] - provide [RevocationState] for each credential
     *
     * @return [ProofInfo] - proof and all reliable data
     */
    /*   fun createProof(
           proofRequest: ProofRequest,
           provideSchema: SchemaProvider,
           provideCredentialDefinition: CredentialDefinitionProvider,
           masterSecretId: String,
           extraQuery: Map<String, Map<String, Any>>?,
           revocationStateProvider: RevocationStateProvider?
       ): ProofInfo
   */
    /**
     * Creates [RevocationState]
     *
     * @param revocationRegistryDefinition [RevocationRegistryDefinition]
     * @param revocationRegistryEntry [RevocationRegistryEntry]
     * @param credentialRevocationId [String]
     * @param timestamp [Long]
     *
     * @return [RevocationState]
     */
    suspend fun createRevocationState(
        revocationRegistryDefinition: RevocationRegistryDefinition,
        revocationRegistryEntry: RevocationRegistryEntry,
        credentialRevocationId: String,
        timestamp: Long
    ): RevocationState

    /**
     * Creates master secret by id
     *
     * @param id [String]
     */
    suspend fun createMasterSecret(id: String)
    fun extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo: CredentialRequestInfo): SharedData
    fun buildCredentialObjectFromRawData(data: SharedData): Credential
    fun buildCredentialOfferObjectFromRawData(data: SharedData): CredentialOffer
    suspend fun removeCredentialExchangeRecordByThread(thread: Thread)
    fun buildPresentationRequestObjectFromRawData(data: SharedData): PresentationRequest
    suspend fun createPresentation(
        presentationRequest: PresentationRequest,
        ledgerConnector: LedgerConnector,
    ): Presentation

    fun extractPresentationDataFromPresentation(presentation: Presentation): SharedData


}
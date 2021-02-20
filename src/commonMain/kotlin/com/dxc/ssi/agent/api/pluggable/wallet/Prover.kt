package com.dxc.ssi.agent.api.pluggable.wallet

/**
 * This entity is able to receive credentials and create proofs about them.
 * Has read-only access to public ledger.
 */
interface Prover : WalletHolder {

    /**
     * Creates credential request
     *
     * @param proverDid [String] - prover's did
     * @param credentialDefinition [CredentialDefinition]
     * @param offer [CredentialOffer] - credential offer
     * @param masterSecretId [String]
     *
     * @return [CredentialRequestInfo] - credential request and all reliable data
     */
/*    fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        offer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo
*/
    /**
     * Stores credential in prover's wallet
     *
     * @param credentialInfo [CredentialInfo] - credential and all reliable data
     * @param credentialRequest [CredentialRequestInfo] - credential request and all reliable data
     * @param offer [CredentialOffer] - credential offer
     * @param credentialDefinition [CredentialDefinition]
     * @param revocationRegistryDefinition [RevocationRegistryDefinition] on [null]
     *
     * @return local UUID of the stored credential in the prover's wallet
     */
  /*  fun receiveCredential(
        credentialInfo: CredentialInfo,
        credentialRequest: CredentialRequestInfo,
        offer: CredentialOffer,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String
*/
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
 /*   fun createRevocationState(
        revocationRegistryDefinition: RevocationRegistryDefinition,
        revocationRegistryEntry: RevocationRegistryEntry,
        credentialRevocationId: String,
        timestamp: Long
    ): RevocationState
*/
    /**
     * Creates master secret by id
     *
     * @param id [String]
     */
    fun createMasterSecret(id: String)
}
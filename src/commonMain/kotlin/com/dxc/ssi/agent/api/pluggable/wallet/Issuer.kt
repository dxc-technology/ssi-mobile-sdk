package com.dxc.ssi.agent.api.pluggable.wallet

import com.dxc.ssi.agent.model.CredentialExchangeRecord

/**
 * This entity is able to create self-signed credentials.
 * Has read/write access to public ledger.
 */
interface Issuer  {

    /**
     * Signs something using wallet and did
     *
     * @param data [String] - data to sign
     * @return [String] - signed data
     */
    fun sign(data: String): String

    /**
     * Creates credential offer
     *
     * @param credentialDefinitionId [CredentialDefinitionId] - credential definition id
     *
     * @return [CredentialOffer] - created credential offer
     */
   // fun createCredentialOffer(credentialDefinitionId: CredentialDefinitionId): CredentialOffer

    /**
     * Issues credential by credential request. If revocation is enabled it will hold one of [maxCredentialNumber].
     *
     * @param credentialRequest [CredentialRequestInfo] - credential request and all reliable info
     * @param proposal [String] - credential proposal
     * @param offer [CredentialOffer] - credential offer
     * @param revocationRegistryId [RevocationRegistryDefinitionId] or [null]
     *
     * @return [CredentialInfo] - credential and all reliable info
     */
  /*  fun issueCredential(
        credentialRequest: CredentialRequestInfo,
        proposal: String,
        offer: CredentialOffer,
        revocationRegistryId: RevocationRegistryDefinitionId?
    ): CredentialInfo
*/
    /**
     * Revokes previously issued credential
     *
     * @param revocationRegistryId [RevocationRegistryDefinitionId] - revocation registry definition id
     * @param credentialRevocationId [String] - revocation registry credential index
     */
 /*   fun revokeCredential(
        revocationRegistryId: RevocationRegistryDefinitionId,
        credentialRevocationId: String
    ): RevocationRegistryEntry
    */

}
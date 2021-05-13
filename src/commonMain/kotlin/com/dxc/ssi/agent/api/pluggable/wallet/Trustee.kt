package com.dxc.ssi.agent.api.pluggable.wallet
/**
 * This entity is able to give another entity an ability to issue new credentials.
 * By default, system has only one top-level-trustee entity, which should share it's rights with others.
 * Hash read-write access to public ledger.
 */
interface Trustee : WalletHolder {
    /**
     * Adds provided identity to whitelist
     *
     * @param identityDetails [IdentityDetails]
     */
 //   fun addKnownIdentities(identityDetails: IdentityDetails)

    /**
     * Creates new schema and stores it to ledger if not exists, else restores schema from ledger
     *
     * @param name [String] - new schema name
     * @param version [String] - schema version
     * @param attributes [List] of [String] - schema attributes
     *
     * @return [Schema]
     */
 //   fun createSchema(name: String, version: String, attributes: List<String>): Schema

    /**
     * Creates credential definition and stores it to ledger if not exists, else restores credential definition from ledger
     *
     * @param schema [Schema] - schema to create credential definition for
     * @param enableRevocation [Boolean] - whether enable or disable revocation for this credential definition
     *
     * @return [CredentialDefinition]
     */
  //  fun createCredentialDefinition(schema: Schema, enableRevocation: Boolean): CredentialDefinition

    /**
     * Creates revocation registry for credential definition if there's no one in ledger
     * (usable only for those credential definition for which enableRevocation = true)
     *
     * @param credentialDefinitionId [CredentialDefinitionId] - credential definition id
     * @param maxCredentialNumber [Int] - maximum number of credentials which can be issued for this credential definition
     *                                  (example) driver agency can produce only 1000 driver licences per year
     *
     * @return [RevocationRegistryInfo]
     */
  /*  fun createRevocationRegistry(
        credentialDefinitionId: CredentialDefinitionId,
        maxCredentialNumber: Int
    ): RevocationRegistryInfo*/
}

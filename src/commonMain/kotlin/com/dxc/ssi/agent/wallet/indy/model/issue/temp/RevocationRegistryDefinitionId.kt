package com.dxc.ssi.agent.wallet.indy.model.issue.temp

import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinitionId

/**
 * Revocation registry definition id is the local identifier of some revocation registry. This class (de)serializes
 * revocation registry definition id.
 * Indy uses raw string value of revocation registry definition id, but it's parts are actually very useful.
 * By possessing some revocation registry definition id id one could also know its:
 *
 * @param did: [String] - the DID of the revocation registry issuer
 * @param credentialDefinitionIdRaw: [IndyCredentialDefinitionId] - the id of credential definition which
 *  this revocation registry accompanies
 * @param tag: [String] - the tag of the revocation registry definition (used for versioning)
 */
//TODO: structurize all code copied from cordentity
data class RevocationRegistryDefinitionId(
    val did: String,
    override val credentialDefinitionIdRaw: String,
    val tag: String
): ContainsCredentialDefinitionId {
    override fun toString() = "$did:4:$credentialDefinitionIdRaw:CL_ACCUM:$tag"

    companion object : FromString<RevocationRegistryDefinitionId> {
        override fun fromString(str: String): RevocationRegistryDefinitionId {
            val strSplitted = str.split(":")
            val didRev = strSplitted[0]
            val tagRev = strSplitted[strSplitted.lastIndex]
            val didCred = strSplitted[2]
            val tagCred = strSplitted[strSplitted.lastIndex - 2]

            val seqNo = strSplitted[5].toInt()

            return RevocationRegistryDefinitionId(didRev, IndyCredentialDefinitionId(didCred, seqNo, tagCred).toString(), tagRev)
        }
    }
}
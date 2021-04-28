package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.FromString
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import kotlinx.serialization.Serializable

/**
 * Credential definition id is the identifier of some credential definition. This class (de)serializes credential
 * definition id. Indy uses raw string value of credential definition id, but it's parts are actually very useful.
 * By possessing some credential definition id one could also know its:
 *
 * @param did: [String] - the DID of the credential definition issuer
 * @param schemaSeqNo: [Int] - the schema's index on ledger
 * @param tag: [String] - the tag of the credential definition (used for versioning)
 */
@Serializable
data class IndyCredentialDefinitionId(val did: String, val schemaSeqNo: Int, val tag: String) : CredentialDefinitionId {
    override fun toString() = "$did:3:CL:$schemaSeqNo:$tag"

    fun getPossibleRevocationRegistryDefinitionId(revTag: String)
            = RevocationRegistryDefinitionId(did, this.toString(), revTag)

    companion object : FromString<IndyCredentialDefinitionId> {
        override fun fromString(str: String): IndyCredentialDefinitionId {
            val strSplitted = str.split(":")

            val didCred = strSplitted[0]
            val tag = strSplitted[strSplitted.lastIndex]

            val seqNo = strSplitted[3].toInt()

            return IndyCredentialDefinitionId(didCred, seqNo, tag)
        }
    }
}

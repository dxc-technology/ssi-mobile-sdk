package com.dxc.ssi.agent.wallet.indy.model.issue.temp

import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinitionId


/**
 * Represents a class which somehow provides credential definition id
 */
//TODO: structurize all code copied from cordentity
//TODO: Move it to package specific to indy
interface ContainsCredentialDefinitionId {
    val credentialDefinitionIdRaw: String
    fun getCredentialDefinitionIdObject(): IndyCredentialDefinitionId = IndyCredentialDefinitionId.fromString(credentialDefinitionIdRaw)
}
package com.dxc.ssi.agent.wallet.indy.model.revoke

import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IndyRevocationRegistryDefinition(
    val ver: String,
    val id: String,
    @SerialName("revocDefType") val revocationRegistryDefinitionType: String,
    val tag: String,
    @SerialName("credDefId") val credentialDefinitionIdRaw: String,
    val value: String
):RevocationRegistryDefinition
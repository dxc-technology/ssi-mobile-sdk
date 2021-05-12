package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedProof(
    @SerialName("revealed_attrs") val revealedAttrs: Map<String, RevealedAttributeReference>,
    @SerialName("self_attested_attrs") val selfAttestedAttrs: Map<String, RevealedAttributeReference>, // not tested
    @SerialName("unrevealed_attrs") val unrevealedAttrs: Map<String, CredentialReference>, // not tested
    val predicates: Map<String, RevealedPredicateReference>
)

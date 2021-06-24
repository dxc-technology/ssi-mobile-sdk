package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedCredentials(
    @SerialName("requested_attributes") val requestedAttributes: Map<String, RequestedAttributeInfo>,
    @SerialName("requested_predicates") val requestedPredicates: Map<String, RequestedPredicateInfo>,
    //TODO: in case of null ensure that this map is serialized as {}
    @SerialName("self_attested_attributes") val selfAttestedAttributes: Map<String, String>
)

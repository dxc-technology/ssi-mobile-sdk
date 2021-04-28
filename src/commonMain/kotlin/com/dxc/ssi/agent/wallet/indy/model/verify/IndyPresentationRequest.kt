package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.didcomm.model.verify.data.PresentationRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * {
"name": "ProofReq",
"version": "2.0",
"nonce": "886317418316603832867162",
"requested_attributes": {
"first-name-requirement": {
"name": "name",
"restrictions": [
{
"schema_name": "vaccination-schema"
}
]
},
"age-requirement": {
"name": "age",
"restrictions": [
{
"schema_name": "vaccination-schema"
}
]
},
"vaccination-requirement": {
"name": "vaccination",
"restrictions": [
{
"schema_name": "vaccination-schema"
}
]
}
},
"requested_predicates": {}
}
 *
 */
//TODO: ensure we parse this request in complex cases with predicartes and attributes
@Serializable
data class IndyPresentationRequest(
    val name: String,
    val version: String,
    val nonce: String,
    @SerialName("requested_attributes") val requestedAttributes: Map<String, CredentialAttributeReference>,
    @SerialName("requested_predicates") val requestedPredicates: Map<String, CredentialPredicateReference>,
    val nonRevoked: Interval? = null
) : PresentationRequest

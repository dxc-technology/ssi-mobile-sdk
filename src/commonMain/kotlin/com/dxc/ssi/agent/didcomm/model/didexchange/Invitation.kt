package com.dxc.ssi.agent.didcomm.model.didexchange

import com.dxc.ssi.agent.utils.UrlSerializer
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
  "label": "Issuer",
  "imageUrl": null,
  "serviceEndpoint": "ws://11.0.1.11:7000/ws",
  "routingKeys": [
    "BQjyramZtWgwLSxKQrHaf7dwbufZQcc3mq3sF9TCWTzT"
  ],
  "recipientKeys": [
    "6PGH4jootfsJVfUfwhoqAYznu8qrSBqmdtWCJXt28DpH"
  ],
  "@id": "dc51b9eb-1f84-4f00-bfc5-0be3f9536b47",
  "@type": "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/connections/1.0/invitation"
}
* */

//TODO: create tests for this class
//TODO: think about some model types instead of just strings
@Serializable
data class Invitation(
    //TODO: set default type here and validate it
    @SerialName("@type") val type: String,
    @SerialName("@id") val id: String,
    val label: String,
    @Serializable(with = UrlSerializer::class)
    val serviceEndpoint: Url,
    val recipientKeys: List<String>,
    val routingKeys: List<String>,
    val imageUrl: String?
)
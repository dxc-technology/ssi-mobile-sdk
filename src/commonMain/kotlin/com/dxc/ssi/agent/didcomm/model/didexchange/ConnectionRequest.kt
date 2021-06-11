package com.dxc.ssi.agent.didcomm.model.didexchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
  "@type": "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/connections/1.0/request",
  "@id": "6e7e3663-714b-40b0-a473-3379d8d85d07",
  "label": "Aries Cloud Agent",
  "connection": {
    "did": "TYetZ9i9TeoXiTPiqYi9gM",
    "did_doc": {
      "@context": "https://w3id.org/did/v1",
      "id": "did:sov:TYetZ9i9TeoXiTPiqYi9gM",
      "publicKey": [
        {
          "id": "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1",
          "type": "Ed25519VerificationKey2018",
          "controller": "did:sov:TYetZ9i9TeoXiTPiqYi9gM",
          "publicKeyBase58": "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"
        }
      ],
      "authentication": [
        {
          "type": "Ed25519SignatureAuthentication2018",
          "publicKey": "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1"
        }
      ],
      "service": [
        {
          "id": "did:sov:TYetZ9i9TeoXiTPiqYi9gM;indy",
          "type": "IndyAgent",
          "priority": 0,
          "recipientKeys": [
            "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"
          ],
          "serviceEndpoint": "http://192.168.0.117:8040"
        }
      ]
    }
  }
}
* */

//TODO: create tests for this class
//TODO: think about some model types instead of just strings
@Serializable
data class ConnectionRequest(
    @SerialName("@type") val type: String,
    @SerialName("@id") val id :String,
    val label: String,
    val imageUrl: String?,
    val connection: Connection
)
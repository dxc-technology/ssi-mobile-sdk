package com.dxc.ssi.agent.didcomm.model.didexchange

import com.dxc.ssi.agent.didcomm.model.common.Service
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* "did_doc": {
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
* */

@Serializable
data class DidDocument(
    @SerialName("@context") val context: String,
    val id: String,
    val publicKey: List<PublicKey>,
    val authentication: List<Authentication>? = null,
    val service: List<Service>? = null
) {
}
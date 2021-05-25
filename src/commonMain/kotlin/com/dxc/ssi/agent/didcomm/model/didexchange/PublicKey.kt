package com.dxc.ssi.agent.didcomm.model.didexchange

import kotlinx.serialization.Serializable

/*
* "publicKey": [
        {
          "id": "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1",
          "type": "Ed25519VerificationKey2018",
          "controller": "did:sov:TYetZ9i9TeoXiTPiqYi9gM",
          "publicKeyBase58": "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"
        }
      ],
* */
@Serializable
data class PublicKey(val id: String,
                     val type: String,
                     val controller: String,
                     val publicKeyBase58: String)
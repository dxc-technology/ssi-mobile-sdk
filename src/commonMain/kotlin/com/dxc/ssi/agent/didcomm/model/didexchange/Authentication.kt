package com.dxc.ssi.agent.didcomm.model.didexchange

import kotlinx.serialization.Serializable

/*
*  "authentication": [
        {
          "type": "Ed25519SignatureAuthentication2018",
          "publicKey": "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1"
        }
      ],
* */
@Serializable
data class Authentication(val type: String, val publicKey: String)
package com.dxc.ssi.agent.didcomm.model.didexchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
*  "connection": {
    "did": "TYetZ9i9TeoXiTPiqYi9gM",
    "did_doc": {

    }
* */
//TODO: think about some model types instead of just strings
//TODO: think if this connection object can/should be merged with Connection that w store in a wallet
@Serializable
data class Connection(
    @SerialName("DID") val did: String,
    @SerialName("DIDDoc") val didDocument : DidDocument)
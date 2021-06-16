package com.dxc.ssi.agent.wallet.indy.libindy

import kotlinx.serialization.Serializable

/*
* {"did":"4PCVFCeZbKXyvgjCedbXDx",
* "verkey":"2qwD1yYC74Q4cVQ714ubBWCAjgTSPhfhZUF3i7sg8gG2",
* "tempVerkey":null,
* "metadata":null}
* */
@Serializable
data class DidWithMetadataResult(
    val did: String,
    val verkey: String,
    val tempVerkey: String?,
    val metadata: String?
)

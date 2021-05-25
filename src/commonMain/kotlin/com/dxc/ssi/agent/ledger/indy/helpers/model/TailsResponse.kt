package com.dxc.ssi.agent.ledger.indy.helpers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tails response message format. Contains JSON-serialized tails file for the given tailsHash.
 * The message object is sent over IndyPartyConnection between Indy parties.
 */
@Serializable
data class TailsResponse(
    @SerialName("tails_hash") val tailsHash: String,
    val tails: Map<String, ByteArray>
)
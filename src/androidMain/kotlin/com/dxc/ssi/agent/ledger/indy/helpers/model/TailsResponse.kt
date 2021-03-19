package com.dxc.ssi.agent.ledger.indy.helpers.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Tails response message format. Contains JSON-serialized tails file for the given tailsHash.
 * The message object is sent over IndyPartyConnection between Indy parties.
 */
data class TailsResponse(
    @JsonProperty("tails_hash") val tailsHash: String,
    val tails: Map<String, ByteArray>
)
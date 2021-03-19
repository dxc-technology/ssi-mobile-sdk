package com.dxc.ssi.agent.ledger.indy.helpers.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Tails request message format. The request is sent over IndyPartyConnection between Indy parties.
 */
data class TailsRequest(
    @JsonProperty("tails_hash") val tailsHash: String
)
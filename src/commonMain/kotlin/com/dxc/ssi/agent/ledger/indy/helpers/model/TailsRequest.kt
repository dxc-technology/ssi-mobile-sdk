package com.dxc.ssi.agent.ledger.indy.helpers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tails request message format. The request is sent over IndyPartyConnection between Indy parties.
 */
@Serializable
data class TailsRequest(
    @SerialName("tails_hash") val tailsHash: String
)
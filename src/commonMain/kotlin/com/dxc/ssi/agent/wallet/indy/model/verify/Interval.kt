package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.Serializable

/**
 * Represents time interval used for non-revocation proof request creation
 */
@Serializable
data class Interval(val from: Long?, val to: Long) {
    companion object {
        fun allTime() = Interval(null, Timestamp.now())
    }
}

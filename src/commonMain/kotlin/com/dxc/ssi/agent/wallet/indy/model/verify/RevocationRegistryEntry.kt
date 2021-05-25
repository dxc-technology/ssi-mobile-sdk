package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.Serializable

/**
 * Represents revocation registry entry
 *
 * Example
 * {
 *  "ver":"1.0",
 *  "value":{
 *      "accum":"true 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0"
 *  }
 * }
 */
@Serializable
data class RevocationRegistryEntry(
    val ver: String,
    val value: String
)


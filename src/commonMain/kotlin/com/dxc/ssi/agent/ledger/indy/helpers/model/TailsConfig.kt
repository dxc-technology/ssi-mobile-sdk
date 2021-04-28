package com.dxc.ssi.agent.ledger.indy.helpers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Allows to configure tails file creation and retrieving
 */
@Serializable
data class TailsConfig(
    @SerialName("base_dir") val baseDir: String,
    @SerialName("uri_path") val uriPattern: String = "")
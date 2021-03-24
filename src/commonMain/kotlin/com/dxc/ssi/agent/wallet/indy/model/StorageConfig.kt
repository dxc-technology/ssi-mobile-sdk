package com.dxc.ssi.agent.wallet.indy.model

import kotlinx.serialization.Serializable

/**
 * Allows to define custom wallet storage path
 */
@Serializable
data class StorageConfig(val path: String)

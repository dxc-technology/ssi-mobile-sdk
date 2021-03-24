package com.dxc.ssi.agent.wallet.indy.model

import kotlinx.serialization.Serializable


/**
 * Represents wallet auth key
 */
@Serializable
data class WalletPassword(val key: String)
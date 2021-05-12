package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.Serializable

@Serializable
data class CredentialAttributeReference(
    val name: String,
    val restrictions: List<Restriction>
)

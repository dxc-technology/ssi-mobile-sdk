package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.Serializable

@Serializable
data class CredentialAttributeReference(
    val name: String? = null,
    val names: List<String>? = null,
    val restrictions: List<Restriction>
) {
    init {
        if ((name == null && names == null) || (name != null && names != null))
            throw RuntimeException("CredentialAttributeReference must contain either \"names\" or \"name\" attribute. Actual name = $name, names = $name")
    }
}


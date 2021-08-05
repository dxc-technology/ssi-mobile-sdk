package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.utils.JsonElementToStringSerializer
import kotlinx.serialization.Serializable

//TODO: structurize all code copied from cordentity
//TODO: Move it to package specific to indy
@Serializable
data class IndyCredentialPubKeys(

    @Serializable(with = JsonElementToStringSerializer::class)
    val primary: String,
    @Serializable(with = JsonElementToStringSerializer::class)
    val revocation: String? = null
)

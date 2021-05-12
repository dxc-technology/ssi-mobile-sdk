package com.dxc.ssi.agent.wallet.indy.model.issue

import com.dxc.ssi.agent.utils.AnyToStringSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.jsonObject

//TODO: structurize all code copied from cordentity
//TODO: Move it to package specific to indy
@Serializable
data class IndyCredentialPubKeys(

    @Serializable(with = AnyToStringSerializer::class)
    val primary: String,
    @Serializable(with = AnyToStringSerializer::class)
    val revocation: String? = null
)

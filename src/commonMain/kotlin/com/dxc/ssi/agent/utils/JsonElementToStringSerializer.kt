package com.dxc.ssi.agent.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.jsonObject

object JsonElementToStringSerializer : KSerializer<String> {
    override fun deserialize(decoder: Decoder): String {
        val jsonInput = decoder as? JsonDecoder ?: error("Can be deserialized only by JSON")
        val json = jsonInput.decodeJsonElement().jsonObject.toString()
        return json
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("anyString", PrimitiveKind.STRING)


    override fun serialize(encoder: Encoder, value: String) {
        val jsonElement = Json.parseToJsonElement(value)
        val jsonEncoder = encoder as? JsonEncoder ?: error("Can be deserialized only by JSON")
        jsonEncoder.encodeJsonElement(jsonElement)

    }

}
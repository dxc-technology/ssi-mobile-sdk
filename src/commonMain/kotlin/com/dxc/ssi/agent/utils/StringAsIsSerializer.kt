package com.dxc.ssi.agent.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
* This serializer is needed for the case when we need to serialize value which might contain json syntax inside and might not
* See usages and tests for more details.
* */
object StringAsIsSerializer : KSerializer<String> {
    override fun deserialize(decoder: Decoder): String {
        return decoder.decodeString()
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("stringAsIs", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }

}
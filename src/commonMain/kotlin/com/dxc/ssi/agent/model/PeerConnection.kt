package com.dxc.ssi.agent.model


import com.dxc.ssi.agent.utils.UrlSerializer
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//TODO: replace String state with some proper enum or reuse DIDExchangeState
//TODO: see if UUID type can be used instead of String for id
@Serializable
data class PeerConnection(
    val id: String,
    val state: String,
    val invitation: String,
    val isSelfInitiated: Boolean,
    val peerRecipientKeys: List<String>,
    val peerVerkey: String? = null,
    val peerDid: String? = null,
    @Serializable(with = UrlSerializer::class)
    val endpoint: Url
) {
    fun toJson(): String = Json.encodeToString(this)

    companion object {
        fun fromJson(jsonString: String): PeerConnection =
            Json { ignoreUnknownKeys = true }.decodeFromString<PeerConnection>(jsonString)
    }
}
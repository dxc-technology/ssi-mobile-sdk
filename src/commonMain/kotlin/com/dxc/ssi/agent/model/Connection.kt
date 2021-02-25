package com.dxc.ssi.agent.model


import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//TODO: replace String state with some proper enum or reuse DIDExchangeState
//TODO: see if UUID type can be used instead of String for id
@Serializable
data class Connection(
    val id: String,
    val state: String,
    val invitation: String,
    val isSelfInitiated: Boolean,
    val peerRecipientKeys: List<String>,
    val endpoint: String
) {
    fun toJson(): String = Json.encodeToString(this)

    companion object {
        fun fromJson(jsonString: String): Connection =
            Json { ignoreUnknownKeys = true }.decodeFromString<Connection>(jsonString)
    }
}
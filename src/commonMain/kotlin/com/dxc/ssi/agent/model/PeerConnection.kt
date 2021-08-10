package com.dxc.ssi.agent.model


import com.dxc.ssi.agent.utils.UrlSerializer
import io.ktor.http.*
import kotlinx.serialization.Serializable

//TODO: replace String state with some proper enum or reuse DIDExchangeState
//TODO: see if UUID type can be used instead of String for id
@Serializable
data class PeerConnection(
    val id: String,
    val state: PeerConnectionState,
    val invitation: String,
    val isSelfInitiated: Boolean,
    val peerRecipientKeys: List<String>,
    val peerVerkey: String? = null,
    val peerDid: String? = null,
    @Serializable(with = UrlSerializer::class)
    val endpoint: Url,
    val keepTransportAlive: Boolean,
    val transportState: ConnectionTransportState
)
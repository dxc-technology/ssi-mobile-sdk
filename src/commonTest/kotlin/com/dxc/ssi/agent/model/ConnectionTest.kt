package com.dxc.ssi.agent.model

import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ConnectionTest {

    @Test
    fun testSerialization() {

        val connection = PeerConnection(
            id = "id",
            state = PeerConnectionState.INVITATION_RECEIVED,
            invitation = "invitation",
            isSelfInitiated = true,
            peerRecipientKeys = listOf("keys"),
            endpoint = Url("ws:\\endpoint:111\\ws"),
            keepTransportAlive = true,
            transportState = ConnectionTransportState.CONNECTED
        )

        val jsonString = Json.encodeToString(connection)

        println(jsonString)

        val connection2 = Json { ignoreUnknownKeys = true }.decodeFromString<PeerConnection>(jsonString)

        assertEquals(connection, connection2)

    }
}
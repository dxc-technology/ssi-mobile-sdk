package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ConnectionTest {
    var logger: Kermit = Kermit(LogcatLogger())
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

        logger.d { jsonString }

        val connection2 = Json { ignoreUnknownKeys = true }.decodeFromString<PeerConnection>(jsonString)

        assertEquals(connection, connection2)

    }
}
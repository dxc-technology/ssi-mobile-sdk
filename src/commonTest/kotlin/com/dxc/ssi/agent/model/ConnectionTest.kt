package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import io.ktor.http.*
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

        val jsonString = connection.toJson()

        logger.log(Severity.Debug,"",null) { jsonString }

        val connection2 = PeerConnection.fromJson(jsonString)

        assertEquals(connection, connection2)

    }
}
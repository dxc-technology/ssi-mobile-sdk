package com.dxc.ssi.agent.model

import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ConnectionTest {

    @Test
    fun testSerialization() {

        val connection = PeerConnection(
            id = "id",
            state = "state",
            invitation = "invitati",
            isSelfInitiated = true,
            peerRecipientKeys = listOf("keys"),
            endpoint = Url("ws:\\endpoint:111\\ws")
        )

        val jsonString = connection.toJson()

        println(jsonString)

        val connection2 = PeerConnection.fromJson(jsonString)

        assertEquals(connection, connection2)

    }
}
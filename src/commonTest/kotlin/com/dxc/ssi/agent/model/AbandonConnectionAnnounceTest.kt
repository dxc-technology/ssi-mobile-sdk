package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.abandon.AbandonConnectionAnnounce
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class AbandonConnectionAnnounceTest {
    @Test
    fun testSerialization() {

        val abandonConnectionAnnounce = AbandonConnectionAnnounce(id = "id")




        val jsonString = Json.encodeToString(abandonConnectionAnnounce)

        println(jsonString)

        val abandonConnectionAnnounce2 = Json.decodeFromString<AbandonConnectionAnnounce>(jsonString)

        assertEquals(abandonConnectionAnnounce, abandonConnectionAnnounce2)

    }

    @Test
    fun testDeSerialization() {

        val jsonString = "{\n" +
                "  \"@type\": \"https://didcomm.org/abandon_connection/1.0/announce\",\n" +
                "  \"@id\": \"c17147d2-ada6-4d3c-a489-dc1e1bf778ab\",\n" +
                "  \"~please_ack\": {}\n" +
                "}"

        val abandonConnectionAnnounce2 = Json.decodeFromString<AbandonConnectionAnnounce>(jsonString)

        println(abandonConnectionAnnounce2)

        assertEquals("c17147d2-ada6-4d3c-a489-dc1e1bf778ab", abandonConnectionAnnounce2.id)


    }
}
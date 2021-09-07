package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.abandon.AbandonConnectionAnnounce
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class AbandonConnectionAnnounceTest {
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    fun testSerialization() {

        val abandonConnectionAnnounce = AbandonConnectionAnnounce(id = "id")




        val jsonString = Json.encodeToString(abandonConnectionAnnounce)

        logger.d { jsonString }

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

        logger.d { abandonConnectionAnnounce2.toString() }

        assertEquals("c17147d2-ada6-4d3c-a489-dc1e1bf778ab", abandonConnectionAnnounce2.id)


    }
}
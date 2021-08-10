package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.wallet.indy.model.verify.Restriction
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import kotlin.test.Test
import kotlin.test.assertEquals

class RestrictionTest {

    @Test
    fun testSerializationOfSimpleValue() {

        val restrictionJson = "{ \"schema_name\": \"vaccination-schema\"}"


        val restriction = Json.decodeFromString<Restriction>(restrictionJson)

        val serializedRestrictionJson = Json.encodeToString(restriction)
        println("Serialized attribute: $serializedRestrictionJson")
        assertEquals(restrictionJson.replace("\n","").replace(" ",""), serializedRestrictionJson)

    }

    @Test
    fun testSerializationOfComplexValue() {

        val restrictionJson = " { \"schema_name\": \"vaccination-schema\",\n" +
                "    \"attr::vaccination-status::value\": \"NO\"}"


        val restriction = Json.decodeFromString<Restriction>(restrictionJson)

        val serializedRestrictionJson = Json.encodeToString(restriction)
        println("Serialized attribute: $serializedRestrictionJson")
        assertEquals(restrictionJson.replace("\n","").replace(" ",""), serializedRestrictionJson)

    }



}
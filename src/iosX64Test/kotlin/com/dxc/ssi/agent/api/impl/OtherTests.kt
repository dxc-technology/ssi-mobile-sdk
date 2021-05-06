package com.dxc.ssi.agent.api.impl

import co.touchlab.stately.collections.IsoMutableMap
import co.touchlab.stately.freeze
import com.dxc.utils.Base64
import com.dxc.utils.System
import kotlin.test.Test
import kotlin.test.assertEquals

class OtherTests {

    private val sentPingsMap = IsoMutableMap<String/*ConnectionId*/, Long /*Timestamp when ping was sent*/>()

    @Test
    fun testIsoMap() {
        val currentTimestamp = System.currentTimeMillis()
        sentPingsMap.keys.filter { key ->  currentTimestamp - sentPingsMap[key]!! > 1000}
       // sentPingsMap.filter { entry -> currentTimestamp - entry.value > 1000 }

       // sentPingsMap.filter { entry -> currentTimestamp - entry.value > 1000 }.keys
    }

    @Test
    fun testBase64Encoding() {
        val plainString = "Hello"

        val base64EncodedString = Base64.plainStringToBase64String(plainString)

        println("base64EncodedString($plainString) = $base64EncodedString")

        val expectedBase64 = "SGVsbG8="

        assertEquals(expectedBase64, base64EncodedString)

    }

    @Test
    fun testBase64Decoding() {

        val base64EncodedString = "SGVsbG8="
        val plainString = Base64.base64StringToPlainString(base64EncodedString)
        val expectedPlainString = "Hello"
        println("plainString($base64EncodedString)= $plainString")

        assertEquals(expectedPlainString, plainString)

    }

}
package com.dxc.ssi.agent.transport

import io.ktor.http.*
import org.junit.Test
import kotlin.test.assertEquals


class EndpointTest {
/*
    @Test
    fun testWs() {

        val expectedHost = "192.168.0.117"
        val expectedPort = 7000
        val expectedProtocol = "ws"
        //val expectedPath =



        val endpoint = "$expectedProtocol://$expectedHost:$expectedPort/ws?c_i=eyJsYWJlbCI"

        val url = Url(endpoint)



        assertEquals(expectedPort, url.port)
        assertEquals(expectedHost, url.host)
        assertEquals(expectedProtocol, url.protocol.name)


    }

    @Test
    fun testWss() {
        val endpoint = "wss://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI"
    }

    @Test
    fun testWsWithDefaultPort() {
        val expectedHost = "192.168.0.117"
        val expectedPort = 7000
        val expectedProtocol = "ws"
        //val expectedPath =


        val endpoint = "$expectedProtocol://$expectedHost/ws?c_i=eyJsYWJlbCI"

        val host = parseHostFromEndpoint(endpoint)
        val port = parsePortFromEndpoint(endpoint)
        val protocol = parseProtocolFromEndpoint(endpoint)
        val path = parsePathFromEndpoint(endpoint)

        assertEquals(expectedPort, port)
        assertEquals(expectedHost, host)
        assertEquals(expectedProtocol, protocol)
    }

    @Test
    fun testWssWithDefaultPort() {
        val endpoint = "wss://192.168.0.117/ws?c_i=eyJsYWJlbCI"
    }


    private fun parseProtocolFromEndpoint(endpoint: String): String {
        return Regex("(^.*):\\/\\/.*:.*$").find(endpoint)!!.groups[1]!!.value
    }

    private fun parsePathFromEndpoint(endpoint: String): String {
        return Regex("^.*:.*:.*(\\/.*$)").find(endpoint)!!.groups[1]!!.value

    }

    private fun parsePortFromEndpoint(endpoint: String): Int {
        return Regex("^.*:.*:(.*)\\/.*$").find(endpoint)!!.groups[1]!!.value.toInt()
    }

    private fun parseHostFromEndpoint(endpoint: String): String {
        return Regex("^.*:\\/\\/(.*):.*$").find(endpoint)!!.groups[1]!!.value
    }
*/
}
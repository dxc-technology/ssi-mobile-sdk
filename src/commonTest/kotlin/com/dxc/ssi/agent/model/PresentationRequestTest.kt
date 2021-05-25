package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.IndyProver
import com.dxc.ssi.agent.wallet.indy.model.verify.IndyPresentationRequest
import kotlinx.serialization.decodeFromString
import kotlin.test.Test

class PresentationRequestTest {

    val indyPresentationRequestJson = "{\n" +
            "  \"name\": \"ProofReq\",\n" +
            "  \"version\": \"1.0\",\n" +
            "  \"nonce\": \"153426310090699210167986\",\n" +
            "  \"requested_attributes\": {\n" +
            "    \"all\": {\n" +
            "      \"names\": [\n" +
            "        \"name\",\n" +
            "        \"vaccination-status\"\n" +
            "      ],\n" +
            "      \"restrictions\": [\n" +
            "        {\n" +
            "          \"schema_name\": \"vaccination-schema\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"requested_predicates\": {}\n" +
            "}"

    @Test
    fun testPresentationRequestDeserialization() {

        val indyPresentationReuqest =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyPresentationRequest>(indyPresentationRequestJson)

    }
}
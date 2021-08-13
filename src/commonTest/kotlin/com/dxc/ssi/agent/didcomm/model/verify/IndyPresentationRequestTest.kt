package com.dxc.ssi.agent.didcomm.model.verify

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinition
import com.dxc.ssi.agent.wallet.indy.model.verify.IndyPresentationRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class IndyPresentationRequestTest {
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    @Ignore
    fun testDeserialization() {

        val presentationRequestJson =
            "{\"name\":\"ProofReq\",\"version\":\"2.0\",\"nonce\":\"886317418316603832867162\",\"requested_attributes\":{\"first-name-requirement\":{\"name\":\"name\",\"restrictions\":[{\"schema_name\":\"vaccination-schema\"}]},\"age-requirement\":{\"name\":\"age\",\"restrictions\":[{\"schema_name\":\"vaccination-schema\"}]},\"vaccination-requirement\":{\"name\":\"vaccination\",\"restrictions\":[{\"schema_name\":\"vaccination-schema\"}]}},\"requested_predicates\":{},\"nonRevoked\":null}"

        logger.d { "raw presentationRequestJson: $presentationRequestJson" }


        val presentationRequest = jsonProcessor.decodeFromString<IndyPresentationRequest>(presentationRequestJson)

        logger.d { "parsed presentation request: $presentationRequest" }

        val encodeToString = jsonProcessor.encodeToString(presentationRequest)

        logger.d { "serialized back presentation request: $presentationRequest" }

        assertEquals(presentationRequestJson, encodeToString)
    }


}
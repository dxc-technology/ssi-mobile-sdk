package com.dxc.ssi.agent.didcomm.model.verify

import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.verify.IndyPresentationRequest
import com.dxc.ssi.agent.wallet.indy.model.verify.RequestedCredentials
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class RequestedCredentialsTest {

    @Test
    fun testDeserialization() {

        val requestedCredentialsJson =
            "{\"requested_attributes\":{\"first-name-requirement\":{\"cred_id\":\"bf2a266a-cc45-40a6-ad3f-9f65408f5c34\",\"revealed\":true,\"timestamp\":null},\"age-requirement\":{\"cred_id\":\"bf2a266a-cc45-40a6-ad3f-9f65408f5c34\",\"revealed\":true,\"timestamp\":null},\"vaccination-requirement\":{\"cred_id\":\"bf2a266a-cc45-40a6-ad3f-9f65408f5c34\",\"revealed\":true,\"timestamp\":null}},\"requested_predicates\":{},\"self_attested_attributes\":{}}"

        println("raw requestedCredentialsJson: $requestedCredentialsJson")


        val presentedCredentials = jsonProcessor.decodeFromString<RequestedCredentials>(requestedCredentialsJson)

        println("parsed presentation request: $presentedCredentials")

        val encodeToString = jsonProcessor.encodeToString(presentedCredentials)

        println("serialized back presentation request: $presentedCredentials")

        assertEquals(requestedCredentialsJson, encodeToString)
    }


}
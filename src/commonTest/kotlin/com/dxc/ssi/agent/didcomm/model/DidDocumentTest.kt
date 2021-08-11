package com.dxc.ssi.agent.didcomm.model

import com.dxc.ssi.agent.didcomm.model.didexchange.Authentication
import com.dxc.ssi.agent.didcomm.model.didexchange.DidDocument
import com.dxc.ssi.agent.didcomm.model.didexchange.PublicKey
import com.dxc.ssi.agent.didcomm.model.common.Service
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class DidDocumentTest {
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    fun testDidDocumentSerialization() {

        val context = "https://w3id.org/did/1"
        val id = "did:sov:TYetZ9i9TeoXiTPiqYi9gM"
        val priority = 0
        val firstRecipientKey = "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"

        val serviceEndpoint = "http://192.168.0.117:8040"
        val serviceId = "did:sov:TYetZ9i9TeoXiTPiqYi9gM;indy"
        val serviceType = "IndyAgent"

        val authenticationType = "Ed25519SignatureAuthentication2018"
        val authenticationPublicKey = "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1"

        val publicKeyId = "did:sov:TYetZ9i9TeoXiTPiqYi9gM#1"
        val publicKeyType = "Ed25519VerificationKey2018"
        val publicKeyController = "did:sov:TYetZ9i9TeoXiTPiqYi9gM"
        val publicKeyPublicKeyBase58 = "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"


        val exampleDidDocument =
            "{\"@context\":\"$context\",\"id\":\"$id\",\"publicKey\":[{\"id\":\"$publicKeyId\",\"type\":\"$publicKeyType\",\"controller\":\"$publicKeyController\",\"publicKeyBase58\":\"$publicKeyPublicKeyBase58\"}],\"authentication\":[{\"type\":\"$authenticationType\",\"publicKey\":\"$authenticationPublicKey\"}],\"service\":[{\"id\":\"$serviceId\",\"type\":\"$serviceType\",\"priority\":$priority,\"recipientKeys\":[\"$firstRecipientKey\"],\"serviceEndpoint\":\"$serviceEndpoint\"}]}"


        val didDocument = DidDocument(
            context = context,
            id = id,
            authentication = listOf(Authentication(type = authenticationType, publicKey = authenticationPublicKey)),
            publicKey = listOf(
                PublicKey(
                    id = publicKeyId,
                    type = publicKeyType,
                    controller = publicKeyController,
                    publicKeyBase58 = publicKeyPublicKeyBase58
                )
            ),
            service = listOf(
                Service(
                    id = serviceId,
                    type = serviceType,
                    priority = priority,
                    recipientKeys = listOf(firstRecipientKey),
                    serviceEndpoint = serviceEndpoint
                )
            )
        )

        val ourDidDocument = Json.encodeToString(didDocument)
        logger.log(Severity.Debug,"",null) { ourDidDocument }

        assertEquals(exampleDidDocument, ourDidDocument, "DID Document does not match expected one after serialization")

        val deserializedDidDocument = Json.decodeFromString<DidDocument>(ourDidDocument)

        assertEquals(
            didDocument,
            deserializedDidDocument,
            "DID Document does not match expected one after deserialization"
        )

    }
}
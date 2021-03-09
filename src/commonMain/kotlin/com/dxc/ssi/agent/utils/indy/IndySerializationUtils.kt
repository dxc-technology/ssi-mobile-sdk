package com.dxc.ssi.agent.utils.indy

import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.wallet.indy.model.issue.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object IndySerializationUtils {
    private val indySerializationModule = SerializersModule {
        polymorphic(CredentialRequest::class) {
            subclass(IndyCredentialRequest::class)
        }
        polymorphic(CredentialRequestMetadata::class) {
            subclass(IndyCredentialRequestMetadata::class)
        }
        polymorphic(Credential::class) {
            subclass(IndyCredential::class)
        }
        polymorphic(CredentialOffer::class) {
            subclass(IndyCredentialOffer::class)
        }
        polymorphic(CredentialDefinition::class) {
            subclass(IndyCredentialDefinition::class)
        }
        polymorphic(CredentialDefinitionId::class) {
            subclass(IndyCredentialDefinitionId::class)
        }
    }

    val jsonProcessor = Json {
        serializersModule = indySerializationModule
        classDiscriminator = "class"
    }


}
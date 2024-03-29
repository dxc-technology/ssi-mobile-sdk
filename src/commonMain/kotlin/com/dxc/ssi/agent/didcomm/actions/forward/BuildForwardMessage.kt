package com.dxc.ssi.agent.didcomm.actions.forward

import com.dxc.ssi.agent.didcomm.model.envelop.EncryptedEnvelop
import com.dxc.ssi.agent.didcomm.model.other.Forward
import com.dxc.ssi.agent.model.messages.MessageEnvelop
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

//TODO: understand where this object should be located. It is not an action aftre all. Or it is?
object BuildForwardMessage {
    fun buildForwardMessage(messageEnvelop: MessageEnvelop, recepientKey: String): Forward {

        return Forward(
            //TODO: check what the id should be
            id = "test_id",
            to = recepientKey,
            msg = Json { ignoreUnknownKeys = true }.decodeFromString<EncryptedEnvelop>(messageEnvelop.payload)
        )
    }
}
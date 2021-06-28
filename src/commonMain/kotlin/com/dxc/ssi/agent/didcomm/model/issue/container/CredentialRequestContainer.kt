package com.dxc.ssi.agent.didcomm.model.issue.container

import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
    "@type": "https://didcomm.org/issue-credential/1.0/request-credential",
    "@id": "<uuid-of-request-message>",
    "comment": "some comment",
    "requests~attach": [
        {
            "@id": "attachment id",
            "mime-type": "application/json",
            "data": {
                "base64": "<bytes for base64>"
            }
        },
    ]
}

* */

//TODO: create tests for this class
//TODO: think about some model types instead of just strings
@Serializable
data class CredentialRequestContainer(
    //TODO: create enum or other holder for message type, replace hardocde and move it inside of the message, as the template will suit only this particular request

    @Required @SerialName("@type") val type: String = "https://didcomm.org/issue-credential/1.0/request-credential",
    @SerialName("@id") val id: String,
    @SerialName("~thread") val thread: Thread,
    @SerialName("requests~attach") val requestsAttach: List<Attach>,
    val comment: String?
)
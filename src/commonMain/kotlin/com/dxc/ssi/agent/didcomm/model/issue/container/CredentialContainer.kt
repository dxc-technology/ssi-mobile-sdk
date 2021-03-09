package com.dxc.ssi.agent.didcomm.model.issue.container

import com.dxc.ssi.agent.didcomm.model.common.Thread
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
    "@type": "https://didcomm.org/issue-credential/1.0/issue-credential",
    "@id": "<uuid-of-issue-message>",
    "comment": "some comment",
    "credentials~attach": [
        {
            "@id": "libindy-cred-0",
            "mime-type": "application/json",
            "data": {
                "base64": "<bytes for base64>"
            }
        }
    ]
}

* */

//TODO: create tests for this class
//TODO: think about some model types instead of just strings
@Serializable
data class CredentialContainer(
    @SerialName("@type") val type: String,
    @SerialName("@id") val id: String,
    @SerialName("credentials~attach") val credentialsAttach: List<Attach>,
    @SerialName("~thread") val thread: Thread,
    val comment: String?
)
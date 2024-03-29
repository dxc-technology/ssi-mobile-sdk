package com.dxc.ssi.agent.didcomm.model.verify.container

import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
    "@type": "https://didcomm.org/present-proof/1.0/presentation",
    "@id": "<uuid-presentation>",
    "comment": "some comment",
    "presentations~attach": [
        {
            "@id": "libindy-presentation-0",
            "mime-type": "application/json",
            "data": {
                "base64": "<bytes for base64>"
            }
        }
    ]
}
* */
@Serializable
data class PresentationContainer(
    //TODO: create enum or other holder for message type, replace hardocde and move it inside of the message, as the template will suit only this particular request
    @Required @SerialName("@type") val type: String = "https://didcomm.org/present-proof/1.0/presentation",
    @SerialName("@id") val id: String,
    @SerialName("presentations~attach") val presentationAttach: List<Attach>,
    @SerialName("~thread") val thread: Thread,
    val comment: String? = null
)

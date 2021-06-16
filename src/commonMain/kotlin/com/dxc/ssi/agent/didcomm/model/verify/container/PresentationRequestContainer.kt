package com.dxc.ssi.agent.didcomm.model.verify.container

import com.dxc.ssi.agent.didcomm.model.common.Attach
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
*{
    "@type": "https://didcomm.org/present-proof/1.0/request-presentation",
    "@id": "<uuid-request>",
    "comment": "some comment",
    "request_presentations~attach": [
        {
            "@id": "libindy-request-presentation-0",
            "mime-type": "application/json",
            "data":  {
                "base64": "<bytes for base64>"
            }
        }
    ]
}

* */

//TODO: create tests for this class
//TODO: think about some model types instead of just strings
@Serializable
data class PresentationRequestContainer(
    @SerialName("@type") val type: String,
    @SerialName("@id") val id: String,
    @SerialName("request_presentations~attach") val presentationRequestAttach: List<Attach>,
    val comment: String? = null
)
package com.dxc.ssi.agent.didcomm.model.issue.container

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
        "name": "vaccination",
        "mime-type": "application/json",
        "value": "yes"
      }
* */
//TODO: think about some model types instead of just strings
@Serializable
data class Attribute(
    val name: String,
    @SerialName("mime-type") val mimeType: String,
    val value: String
)

package com.dxc.ssi.agent.didcomm.model.abandon

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* {
  "@type": "https://didcomm.org/abandon_connection/1.0/announce",
  "@id": "c17147d2-ada6-4d3c-a489-dc1e1bf778ab",
  "~please_ack": {}
}
* */

@Serializable
data class AbandonConnectionAnnounce(
    @Required @SerialName("@type") val type: String = "https://didcomm.org/abandon_connection/1.0/announce",
    @SerialName("@id") val id: String,
    //TODO: for now we won;t parse this. After we need to make change it to Boolean and parse presense of {} to true and lack to false
    @SerialName("~please_ack") val pleaseAck: Unit? = null
)
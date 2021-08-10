package com.dxc.ssi.agent.didcomm.model.ack

import com.dxc.ssi.agent.didcomm.model.common.Thread
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO: understand purpose of this class and whether it will be common for all types of processes or there should be separate Ack for each process

/*
{
  "@type": "https://didcomm.org/notification/1.0/ack",
  "@id": "06d474e0-20d3-4cbf-bea6-6ba7e1891240",
  "status": "OK",
  "~thread": {
    "thid": "b271c889-a306-4737-81e6-6b2f2f8062ae",
    "sender_order": 4,
    "received_orders": {"did:sov:abcxyz": 3}
  }
}
* */
@Serializable
data class Ack(
    @Required @SerialName("@type") val type: String = "https://didcomm.org/notification/1.0/ack",
    @SerialName("@id") val id: String,
    @SerialName("~thread") val thread: Thread? = null
)

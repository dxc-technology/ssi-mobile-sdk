package com.dxc.ssi.agent.didcomm.model.common

import com.dxc.ssi.agent.model.SharedConnection
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*
*  "service": [
        {
          "id": "did:sov:TYetZ9i9TeoXiTPiqYi9gM;indy",
          "type": "IndyAgent",
          "priority": 0,
          "recipientKeys": [
            "FUAUXqejkdnX9sAwvqk7dbUpThpoo5r9BH7cjBiLEtHB"
          ],
          "serviceEndpoint": "http://192.168.0.117:8040"
        }
      ]
* */

//TODO: See if special types instead of basic strings are needed
@Serializable
data class Service(
    val id: String,
    val type: String,
    val priority: Int,
    val recipientKeys :List<String>,
    val serviceEndpoint: String
) {

    /*fun toJson(): String = Json.encodeToString(this)

    companion object {
        fun fromJson(jsonString: String): Service =
            Json { ignoreUnknownKeys = true }.decodeFromString<Service>(jsonString)
    }
    */

}
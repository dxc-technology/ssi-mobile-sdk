package com.dxc.ssi.agent.didcomm.model.issue.container

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
*  "credential_preview": {
    "attributes": [
      {
        "name": "name",
        "mime-type": "application/json",
        "value": "Alice Smith"
      },
      {
        "name": "age",
        "mime-type": "application/json",
        "value": "18"
      },
      {
        "name": "vaccination",
        "mime-type": "application/json",
        "value": "yes"
      }
    ],
    "@id": null,
    "@type": "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/issue-credential/1.0/credential-preview"
  },
* */
//TODO: think about some model types instead of just strings
@Serializable
data class CredentialPreview(
    //TODO: provide fault and validate it
    @SerialName("@type") val type: String,
    @SerialName("@id") val id: String?,
    val attributes: List<Attribute>
)

package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* "revealed_attr_groups": 
*
*   revealed_attr_groups\":
*       {
          "all\":
                {
                "sub_proof_index\": 0,
                "values\":
                    {
                        "name\":
                            {
                            "raw\": \"Pac Man\",
                            "encoded\": \"7849337780549251643922512800903154783438340395516986688435569540035163987433\"
                            },
                        "vaccination-status\":
                            {
                            "raw\": \"YES\",
                            "encoded\": \"112666656814801354982218079345417849231441546163497954489065110424009583481495\"
                            }
                    }
                }
        },
* 
* */

@Serializable
data class RevealedAttributeGroupReference(
    @SerialName("sub_proof_index") val subProofIndex: Int,
    val values: Map<String, RevealedAttributeValue>
)

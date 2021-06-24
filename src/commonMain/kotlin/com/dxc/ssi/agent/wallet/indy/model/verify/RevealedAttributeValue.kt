package com.dxc.ssi.agent.wallet.indy.model.verify

import kotlinx.serialization.Serializable

/*
* {
                            "raw\": \"Pac Man\",
                            "encoded\": \"7849337780549251643922512800903154783438340395516986688435569540035163987433\"
                            }
* */
@Serializable
data class RevealedAttributeValue(
    val raw: String,
    val encoded: String
)

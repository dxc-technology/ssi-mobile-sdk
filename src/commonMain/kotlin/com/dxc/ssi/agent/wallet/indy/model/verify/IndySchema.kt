package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.didcomm.model.verify.data.Schema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndySchema(
    val ver: String,
    val id: String,
    val name: String,
    val version: String,
    @SerialName("attrNames") val attributeNames: List<String>,
    @SerialName("seqNo") val seqNo: Int?,
): Schema

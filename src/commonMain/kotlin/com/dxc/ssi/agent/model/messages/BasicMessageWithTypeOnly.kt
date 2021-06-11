package com.dxc.ssi.agent.model.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicMessageWithTypeOnly(@SerialName("@type") val type: String)
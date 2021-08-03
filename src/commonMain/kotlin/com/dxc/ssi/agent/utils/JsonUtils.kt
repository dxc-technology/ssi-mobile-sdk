package com.dxc.ssi.agent.utils

import com.dxc.utils.Base64

object JsonUtils {
    fun extractValue(retrievedValue: String?): String {
        return Regex("value\":\"(.*\\})\",").find(retrievedValue!!)!!.groups[1]!!.value.replace("\\", "")
    }

    fun extractBase64Value(retrievedValue: String?): String {
        return Base64.base64StringToPlainString(Regex("value\":\"(.*\\})\",").find(retrievedValue!!)!!.groups[1]!!.value)
    }
}
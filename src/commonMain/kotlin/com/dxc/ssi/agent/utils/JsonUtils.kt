package com.dxc.ssi.agent.utils

object JsonUtils {
    fun extractValue(retrievedValue: String?): String {
        return Regex("value\":\"(.*})\",").find(retrievedValue!!)!!.groups[1]!!.value.replace("\\", "")
    }
}
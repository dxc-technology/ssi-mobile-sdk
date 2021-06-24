package com.dxc.utils

expect class System {
    companion object {
        fun currentTimeMillis(): Long
        fun getEnv(key: String): String?
        fun getProperty(key: String): String?
        fun setEnv(key: String, value: String)
        fun getCurrentThread(): String
    }
}
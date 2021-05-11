package com.dxc.utils

expect class System {
    companion object {
        fun currentTimeMillis(): Long
        fun getEnv(key: String): String?
        fun getProperty(key: String): String?
        //TODO: move to some other class like IndySystem or smth like that as it is not system wide
        fun getIndyHomePath(): String
    }
}
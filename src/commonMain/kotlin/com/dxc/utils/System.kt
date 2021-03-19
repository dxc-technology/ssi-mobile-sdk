package com.dxc.utils

expect class System {
    companion object {
        fun currentTimeMillis(): Long
    }
}
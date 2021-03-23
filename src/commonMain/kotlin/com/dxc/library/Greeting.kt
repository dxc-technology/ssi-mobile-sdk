package com.dxc.library

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

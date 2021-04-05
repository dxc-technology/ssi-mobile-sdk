package com.dxc.ssi.agent.utils

expect class PlatformInit() {
    //TODO: move init method to  companion object as we do not need state here
    fun init()
}
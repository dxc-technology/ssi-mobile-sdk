package com.dxc.ssi.agent.transport

//TODO: make this class more common and move it out of transport package
expect class Sleeper() {
    fun sleep(value: Int)
}
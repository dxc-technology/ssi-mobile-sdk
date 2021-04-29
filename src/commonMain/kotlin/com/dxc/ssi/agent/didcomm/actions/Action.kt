package com.dxc.ssi.agent.didcomm.actions

interface Action {
    suspend fun perform(): ActionResult

}

package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.actions.ActionParams

//TODO: move this to some common level
interface MessageType {
    fun getTypeString(): String
    fun getMessageHandler() : (ActionParams) -> Action
}

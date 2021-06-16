package com.dxc.ssi.agent.didcomm.actions

import com.dxc.ssi.agent.model.SharedConnection

//TODO: think about more generic action results
//TODO: think if we need such a generic action at all
data class ActionResult(val connection: SharedConnection? = null,
                        val trustPingSuccessful: Boolean? = null) {

}

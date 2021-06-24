package com.dxc.ssi.agent.didcomm.actions

import com.dxc.ssi.agent.model.PeerConnection

//TODO: think about more generic action results
//TODO: think if we need such a generic action at all
data class ActionResult(val connection: PeerConnection? = null,
                        val trustPingSuccessful: Boolean? = null) {

}

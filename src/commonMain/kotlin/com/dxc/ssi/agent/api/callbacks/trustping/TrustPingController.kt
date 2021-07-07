package com.dxc.ssi.agent.api.callbacks.trustping


import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.model.PeerConnection

interface TrustPingController {
    //TODO: implement responder action for trust ping
    fun onTrustPingReceived(connection: PeerConnection): ActionResult
    fun onTrustPingSent(connection: PeerConnection)
    fun onTrustPingResponseDidNotReceived(connection: PeerConnection)
    fun onTrustPingResponseReceived(connection: PeerConnection)
}

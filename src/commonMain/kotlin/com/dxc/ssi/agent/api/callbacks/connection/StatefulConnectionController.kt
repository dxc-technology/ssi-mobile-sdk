package com.dxc.ssi.agent.api.callbacks.connection


import com.dxc.ssi.agent.model.PeerConnection

interface StatefulConnectionController {
    fun onReconnected(connection: PeerConnection)
    fun onDisconnected(connection: PeerConnection)
}

package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import kotlinx.serialization.Serializable

@Serializable
data class PeerConnectionRecord(val peerConnection: PeerConnection) : WalletStorable {
    override val id: String
        get() = peerConnection.id

    override fun generateTagsJson(): String {
        return "{\"${WalletRecordTag.ConnectionVerKey.name}\": \"${peerConnection.peerVerkey}\", \"${WalletRecordTag.ConnectionState.name}\": \"${peerConnection.state.name}\"}"
    }
}
package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationState
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import kotlinx.serialization.Serializable


@Serializable
//TODO: understand which of those fields we really need
data class PresentationExchangeRecord(
    val state: CredentialVerificationState,
    val connectionId: String,
    override val thread: Thread,
    val presentationRequestContainer: PresentationRequestContainer? = null,
    val isParked: Boolean = false
) : ExchangeRecord {
    override fun generateTagsJson() = "{\"${WalletRecordTag.PresentationExchangeRecordState.name}\": \"${state.name}\"}"

}
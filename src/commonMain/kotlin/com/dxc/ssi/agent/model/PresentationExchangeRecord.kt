package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequestInfo
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceState
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationState
import kotlinx.serialization.Serializable


@Serializable
//TODO: understand which of those fields we really need
data class PresentationExchangeRecord(
    val state: CredentialVerificationState,
    val connectionId: String,
    val thread: Thread,
    val presentationRequestContainer: PresentationRequestContainer? = null,
    val isParked: Boolean = false
)

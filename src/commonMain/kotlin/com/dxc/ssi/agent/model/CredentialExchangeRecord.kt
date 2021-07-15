package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequestInfo
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceState
import kotlinx.serialization.Serializable


@Serializable
//TODO: understand which of those fields we really need
data class CredentialExchangeRecord(
    val state: CredentialIssuenceState,
    val connectionId: String,
    val thread: Thread,
    val credentialOfferContainer: CredentialOfferContainer? = null,
    val credentialRequestContainer: CredentialRequestContainer? = null,
    val credentialRequestInfo: CredentialRequestInfo? = null,
    //TODO: check if this property or all othe rproperties are generic and are abstracted form Indy. Follow Container-Data pattern
    val credentialDefinition: CredentialDefinition,
    val isParked: Boolean = false
)

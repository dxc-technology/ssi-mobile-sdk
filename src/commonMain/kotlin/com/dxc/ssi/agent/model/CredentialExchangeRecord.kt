package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialRequestInfo
import kotlinx.serialization.Serializable


@Serializable
//TODO: understand which of those fields we really need
data class CredentialExchangeRecord(
    //TODO:replace state data type to enum
    val state: String,
    val connectionId: String,
    val thread: Thread,
    val credentialOfferContainer: CredentialOfferContainer,
    val credentialRequestContainer: CredentialRequestContainer,
    val credentialRequestInfo: CredentialRequestInfo,
    //TODO: check if this property or all othe rproperties are generic and are abstracted form Indy. Follow Container-Data pattern
    val credentialDefinition: CredentialDefinition
)

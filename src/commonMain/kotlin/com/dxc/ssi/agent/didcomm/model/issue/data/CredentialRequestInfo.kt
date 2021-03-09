package com.dxc.ssi.agent.didcomm.model.issue.data

import kotlinx.serialization.Serializable

@Serializable
data class CredentialRequestInfo(
    val credentialRequest: CredentialRequest,
    val credentialRequestMetadata: CredentialRequestMetadata
)
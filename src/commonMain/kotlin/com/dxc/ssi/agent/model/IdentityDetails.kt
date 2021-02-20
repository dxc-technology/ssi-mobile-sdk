package com.dxc.ssi.agent.model

data class IdentityDetails(
    val did: String,
    val verkey: String,
    //TODO: add JSON ignore
    val alias: String?,
    val role: String?
)

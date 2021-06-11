package com.dxc.ssi.agent.didcomm.states.issue

enum class CredentialIssuenceState {
    PROPOSAL_SENT,
    PROPOSAL_RECEIVED,
    OFFER_SENT,
    OFFER_RECEIVED,
    CREDENTIAL_SENT,
    CREDENTIAL_RECEIVED,
    DONE
}
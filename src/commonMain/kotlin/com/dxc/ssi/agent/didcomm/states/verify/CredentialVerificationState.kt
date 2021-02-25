package com.dxc.ssi.agent.didcomm.states.verify

enum class CredentialVerificationState {
    PROPOSAL_SENT,
    PROPOSAL_RECEIVED,
    REQUEST_SENT,
    REQUEST_RECEIVED,
    PRESENTATION_SENT,
    PRESENTATION_RECEIVED,
    REJECT_SENT,
    REJECT_RECEIVED,
    DONE
}
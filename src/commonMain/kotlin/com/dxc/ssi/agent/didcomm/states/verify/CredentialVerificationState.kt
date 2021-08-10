package com.dxc.ssi.agent.didcomm.states.verify

import com.dxc.ssi.agent.didcomm.states.State

enum class CredentialVerificationState:State {
    PROPOSAL_SENT,
    PROPOSAL_RECEIVED,
    REQUEST_SENT,
    REQUEST_RECEIVED,
    REQUEST_REJECTED,
    PRESENTATION_SENT,
    PRESENTATION_RECEIVED,
    REJECT_SENT,
    REJECT_RECEIVED,
    DONE
}
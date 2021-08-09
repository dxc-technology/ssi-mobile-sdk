package com.dxc.ssi.agent.didcomm.states.issue

import com.dxc.ssi.agent.didcomm.states.State

enum class CredentialIssuenceState: State {
    PROPOSAL_SENT,
    PROPOSAL_RECEIVED,
    OFFER_SENT,
    OFFER_RECEIVED,
    REQUEST_SENT,
    REQUEST_RECEIVED,
    CREDENTIAL_SENT,
    CREDENTIAL_RECEIVED,
    DONE
}
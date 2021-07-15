package com.dxc.ssi.agent.api.callbacks.issue

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection

interface CredReceiverController {
    /*
    fun onProposalSent(connection: Connection, credentialProposal: CredentialProposal): CallbackResult*/

    fun onOfferReceived(connection: PeerConnection, credentialOfferContainer: CredentialOfferContainer): OfferResponseAction
    fun onRequestSent(connection: PeerConnection, credentialRequestContainer: CredentialRequestContainer): CallbackResult
    fun onCredentialReceived(connection: PeerConnection, credentialContainer: CredentialContainer): CallbackResult
    fun onDone(connection: PeerConnection, credentialContainer: CredentialContainer): CallbackResult
    fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult

}

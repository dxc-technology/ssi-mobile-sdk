package com.dxc.ssi.agent.api.callbacks.verification

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PresentationRequestResponseAction

interface CredPresenterController {
    fun onRequestReceived(connection: PeerConnection, presentationRequestContainer: PresentationRequestContainer): PresentationRequestResponseAction
    //fun onPresentationSent(connection: PeerConnection, presentation: PresentationContainer): CallbackResult
    fun onDone(connection: PeerConnection): CallbackResult
    fun onProblemReportGenerated(connection: PeerConnection, problemReport: ProblemReport)

/*
    fun onPresentationProposalSent(connection: PeerConnection, presentationProposal: PresentationProposal): CallbackResult
    fun onPresentationAckReceived(connection: PeerConnection, presentationProposal: PresentationAck): CallbackResult
    fun onProblemReportReceived(connection: PeerConnection, problemReport: ProblemReport): CallbackResult
     */
}

package com.dxc.ssi.agent.api.callbacks.verification

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.SharedConnection

interface CredPresenterController {
    fun onRequestReceived(connection: SharedConnection, presentationRequest: PresentationRequestContainer): CallbackResult
    //fun onPresentationSent(connection: SharedConnection, presentation: PresentationContainer): CallbackResult
    fun onDone(connection: SharedConnection): CallbackResult
    fun onProblemReportGenerated(connection: SharedConnection, problemReport: ProblemReport)

/*
    fun onPresentationProposalSent(connection: SharedConnection, presentationProposal: PresentationProposal): CallbackResult
    fun onPresentationAckReceived(connection: SharedConnection, presentationProposal: PresentationAck): CallbackResult
    fun onProblemReportReceived(connection: SharedConnection, problemReport: ProblemReport): CallbackResult
     */
}

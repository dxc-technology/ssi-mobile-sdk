package com.dxc.ssi.agent.api.callbacks.verification

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.Connection

interface CredPresenterController {
    fun onRequestReceived(connection: Connection, presentationRequest: PresentationRequestContainer): CallbackResult
    //fun onPresentationSent(connection: Connection, presentation: PresentationContainer): CallbackResult
    fun onDone(connection: Connection): CallbackResult
    fun onProblemReportGenerated(connection: Connection, problemReport: ProblemReport)

/*
    fun onPresentationProposalSent(connection: Connection, presentationProposal: PresentationProposal): CallbackResult
    fun onPresentationAckReceived(connection: Connection, presentationProposal: PresentationAck): CallbackResult
    fun onProblemReportReceived(connection: Connection, problemReport: ProblemReport): CallbackResult
     */
}

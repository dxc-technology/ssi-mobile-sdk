package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.PeerConnection

class CredPresenterControllerImpl : CredPresenterController {
    override fun onRequestReceived(
        connection: PeerConnection,
        presentationRequest: PresentationRequestContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onDone(connection: PeerConnection): CallbackResult {
        return CallbackResult(true)
    }

    override fun onProblemReportGenerated(connection: PeerConnection, problemReport: ProblemReport) {

    }

}
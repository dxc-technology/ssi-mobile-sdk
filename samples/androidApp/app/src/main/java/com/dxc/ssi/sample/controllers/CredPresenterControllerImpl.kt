package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.Connection

class CredPresenterControllerImpl : CredPresenterController {
    override fun onRequestReceived(
        connection: Connection,
        presentationRequest: PresentationRequestContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onDone(connection: Connection): CallbackResult {
        return CallbackResult(true)
    }

}
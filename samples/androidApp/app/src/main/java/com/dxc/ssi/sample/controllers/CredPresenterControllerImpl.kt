package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.SharedConnection

class CredPresenterControllerImpl : CredPresenterController {
    override fun onRequestReceived(
        connection: SharedConnection,
        presentationRequest: PresentationRequestContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onDone(connection: SharedConnection): CallbackResult {
        return CallbackResult(true)
    }

}
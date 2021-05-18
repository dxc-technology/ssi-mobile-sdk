package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.model.Connection

class CredReceiverControllerImpl : CredReceiverController {
    override fun onOfferReceived(
        connection: Connection,
        credentialOfferContainer: CredentialOfferContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onRequestSent(
        connection: Connection,
        credentialRequestContainer: CredentialRequestContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onCredentialReceived(
        connection: Connection,
        credentialContainer: CredentialContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onDone(connection: Connection, credentialContainer: CredentialContainer): CallbackResult {
        return CallbackResult(true)
    }


}
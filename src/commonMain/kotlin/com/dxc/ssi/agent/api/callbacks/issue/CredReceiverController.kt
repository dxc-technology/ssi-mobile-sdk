package com.dxc.ssi.agent.api.callbacks.issue

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.model.Connection

interface CredReceiverController {
    /*
    fun onProposalSent(connection: Connection, credentialProposal: CredentialProposal): CallbackResult*/

    fun onOfferReceived(connection: Connection, credentialOfferContainer: CredentialOfferContainer): CallbackResult
    fun onRequestSent(connection: Connection, credentialRequestContainer: CredentialRequestContainer): CallbackResult
    fun onCredentialReceived(connection: Connection, credentialContainer: CredentialContainer): CallbackResult
    fun onDone(connection: Connection, credentialContainer: CredentialContainer): CallbackResult

    //TODO: add on ProblemreportSent???

}

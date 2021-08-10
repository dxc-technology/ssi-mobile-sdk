package com.dxc.ssi.sample.controllers

import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.didcomm.model.ack.Ack
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.sample.ssiAgentApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity

class CredReceiverControllerImpl : CredReceiverController {
    var logger: Kermit = Kermit(LogcatLogger())
    override fun onOfferReceived(
        connection: PeerConnection,
        credentialOfferContainer: CredentialOfferContainer
    ): OfferResponseAction {

        logger.log(Severity.Debug,"",null) { "Received credential offer" }

        GlobalScope.launch {

            delay(20_000)
            ssiAgentApi!!.getParkedCredentialOffers()
                .forEach {
                    ssiAgentApi!!.processParkedCredentialOffer(it, OfferResponseAction.ACCEPT)
                }

        }

        return OfferResponseAction.PARK
    }

    override fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult {
     return  CallbackResult(true)
    }

    override fun onRequestSent(
        connection: PeerConnection,
        credentialRequestContainer: CredentialRequestContainer
    ) {

    }

    override fun onCredentialReceived(
        connection: PeerConnection,
        credentialContainer: CredentialContainer
    ): CallbackResult {
        return CallbackResult(true)
    }

    override fun onDone(connection: PeerConnection, credentialContainer: CredentialContainer) {

    }

    override fun onAckSent(connection: PeerConnection, ack: Ack) {
        logger.log(Severity.Debug,"",null) { "Ack sent for credential" }
    }


}
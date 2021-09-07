package com.dxc.ssi.agent.api

import com.dxc.ssi.agent.api.callbacks.connection.StatefulConnectionController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionResponderController
import com.dxc.ssi.agent.api.callbacks.issue.CredIssuerController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.trustping.TrustPingController
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.callbacks.verification.CredVerifierController

data class Callbacks(
    val connectionInitiatorController: ConnectionInitiatorController?,
    val connectionResponderController: ConnectionResponderController?,
    val credReceiverController: CredReceiverController?,
    val credIssuerController: CredIssuerController?,
    val credPresenterController: CredPresenterController?,
    val credVerifierController: CredVerifierController?,
    val statefulConnectionController: StatefulConnectionController?,
    val trustPingController: TrustPingController?
)

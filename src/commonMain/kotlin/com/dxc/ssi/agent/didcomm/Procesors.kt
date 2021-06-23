package com.dxc.ssi.agent.didcomm

import com.dxc.ssi.agent.didcomm.processor.abandon.AbandonConnectionProcessor
import com.dxc.ssi.agent.didcomm.processor.didexchange.DidExchangeProcessor
import com.dxc.ssi.agent.didcomm.processor.issue.CredIssuerProcessor
import com.dxc.ssi.agent.didcomm.processor.trustping.TrustPingProcessor
import com.dxc.ssi.agent.didcomm.processor.verify.CredVerifierProcessor

data class Processors(
    var trustPingProcessor: TrustPingProcessor? = null,
    var abandonConnectionProcessor: AbandonConnectionProcessor? = null,
    var credIssuerProcessor: CredIssuerProcessor? = null,
    var credVerifierProcessor: CredVerifierProcessor? = null,
    var didExchangeProcessor: DidExchangeProcessor? = null
)
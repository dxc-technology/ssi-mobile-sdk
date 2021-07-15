package com.dxc.ssi.agent.didcomm.processor.issue

import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.processor.MessageProcessor
import com.dxc.ssi.agent.model.OfferResponseAction

interface CredIssuerProcessor: MessageProcessor {
    suspend fun processParkedCredentialOffer(credentialOfferContainer: CredentialOfferContainer, offerResponseAction: OfferResponseAction)

}

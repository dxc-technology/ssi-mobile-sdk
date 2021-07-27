package com.dxc.ssi.agent.didcomm.processor.verify

import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.processor.MessageProcessor
import com.dxc.ssi.agent.model.PresentationRequestResponseAction

interface CredVerifierProcessor : MessageProcessor {
    suspend fun processParkedPresentationRequest(presentationRequestContainer: PresentationRequestContainer, presentationRequestResponseAction: PresentationRequestResponseAction)


}

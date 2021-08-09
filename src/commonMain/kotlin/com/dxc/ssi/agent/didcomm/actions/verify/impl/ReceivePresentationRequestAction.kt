package com.dxc.ssi.agent.didcomm.actions.verify.impl

import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.verify.CredentialVerificationAction
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationState
import com.dxc.ssi.agent.model.PresentationExchangeRecord
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ReceivePresentationRequestAction(
    private val actionParams: ActionParams
) : CredentialVerificationAction {
    override suspend fun perform(): ActionResult {

        val messageContext = actionParams.context
        val connection = actionParams.context!!.connection!!
        val walletConnector = actionParams.walletConnector

        val presentationRequestContainer = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<PresentationRequestContainer>(messageContext!!.receivedUnpackedMessage!!.message)

        val existingPresentationRequestRecord =
            walletConnector.prover!!.getPresentationExchangeRecordByThread(Thread(thid = presentationRequestContainer.id))
        if (existingPresentationRequestRecord != null) throw IllegalStateException()

        val presentationRequest =
            walletConnector.prover!!.buildPresentationRequestObjectFromRawData(
                //TODO: deal with several attachemnts
                presentationRequestContainer.presentationRequestAttach[0].data
            )

        walletConnector.prover.storePresentationExchangeRecord(
            PresentationExchangeRecord(
                state = CredentialVerificationState.REQUEST_RECEIVED,
                connectionId = connection.id,
                presentationRequestContainer = presentationRequestContainer,
                thread = Thread(thid = presentationRequestContainer.id)
            )
        )

        return ProcessPresentationRequestAction(actionParams, presentationRequestContainer).perform()


    }


}

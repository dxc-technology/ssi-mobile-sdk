package com.dxc.ssi.agent.didcomm.actions.verify.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.verify.CredentialVerificationAction
import com.dxc.ssi.agent.didcomm.commoon.MessagePacker
import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationContainer
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import kotlinx.serialization.encodeToString

class ReceivePresentationRequestAction(
    private val walletConnector: WalletConnector,
    private val ledgerConnector: LedgerConnector,
    private val transport: Transport,
    private val credPresenterController: CredPresenterController,
    private val presentationRequestMessage: PresentationRequestContainer,
    private val connection: Connection
) : CredentialVerificationAction {
    override fun perform(): ActionResult {

        if (credPresenterController
                .onRequestReceived(connection, presentationRequestMessage).canProceedFurther
        ) {
            //2. Form and Send presentation
            val presentationRequest =
                walletConnector.prover!!.buildPresentationRequestObjectFromRawData(
                    //TODO: deal with several attachemnts
                    presentationRequestMessage.presentationRequestAttach[0].data
                )

            val presentationData = walletConnector.prover!!.createPresentation(presentationRequest, ledgerConnector)

            val presentation = PresentationContainer(
                //TODO: create enum or other holder for message type, replace hardocde and move it inside of the message, as the template will suit only this particular request
                type = "https://didcomm.org/present-proof/1.0/presentation",
                //TODO: set proper id
                id = uuid4().toString(),
                thread = Thread(thid = presentationRequestMessage.id),
                presentationAttach = listOf(
                    Attach(
                        id = "libindy-presentation-0",
                        mimeType = "application/json",
                        data = walletConnector.prover!!.extractPresentationDataFromPresentation(presentationData)

                    )
                ),
                //TODO: think if we need to set some meaningful comment here
                comment = "comment"
            )

            val messageToSend = MessagePacker.packAndPrepareForwardMessage(
                Message(jsonProcessor.encodeToString(presentation)),
                connection,
                walletConnector
            )


            transport.sendMessage(connection, messageToSend)

            credPresenterController.onDone(connection)

        } else {
            //TODO: Send PresentationProposal
        }

        return ActionResult()

    }


}

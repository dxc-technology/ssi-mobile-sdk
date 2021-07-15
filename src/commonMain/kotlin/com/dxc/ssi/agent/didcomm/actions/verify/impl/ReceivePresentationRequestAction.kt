package com.dxc.ssi.agent.didcomm.actions.verify.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.verify.CredentialVerificationAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationContainer
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.exceptions.common.NoCredentialToSatisfyPresentationRequestException
import com.dxc.ssi.agent.model.messages.Message
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ReceivePresentationRequestAction(
    private val actionParams: ActionParams
) : CredentialVerificationAction {
    override suspend fun perform(): ActionResult {

        val messageContext = actionParams.context
        val credPresenterController = actionParams.callbacks.credPresenterController!!
        val connection = actionParams.context!!.connection!!
        val walletConnector = actionParams.walletConnector
        val ledgerConnector = actionParams.ledgerConnector
        val transport = actionParams.transport
        val services = actionParams.services

        val presentationRequestMessage = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<PresentationRequestContainer>(messageContext!!.receivedUnpackedMessage!!.message)

        if (credPresenterController
                .onRequestReceived(connection, presentationRequestMessage).canProceedFurther
        ) {

            val presentationRequest =
                walletConnector.prover!!.buildPresentationRequestObjectFromRawData(
                    //TODO: deal with several attachemnts
                    presentationRequestMessage.presentationRequestAttach[0].data
                )

            try {
                val presentationData = walletConnector.prover!!.createPresentation(presentationRequest, ledgerConnector)

                val presentation = PresentationContainer(
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

                MessageSender.packAndSendMessage(
                    Message(Json.encodeToString(presentation)),
                    connection,
                    walletConnector,
                    transport,
                    services,
                    onMessageSent = {
                        credPresenterController.onDone(connection)
                        null
                    },
                    onMessageSendingFailure = {
                        val problemReport = ProblemReport(
                            id = uuid4().toString(),
                            description = DidCommProblemCodes.COULD_NOT_SEND_PRESENTATION.toProblemReportDescription()
                        )

                        //TODO: make this callback async
                        credPresenterController.onProblemReportGenerated(connection, problemReport)
                        null
                    }
                )


            } catch (e: NoCredentialToSatisfyPresentationRequestException) {

                val problemReport = ProblemReport(
                    id = uuid4().toString(),
                    description = DidCommProblemCodes.NO_CREDENTIALS_TO_SATISFY_PRESENTATION_REQUEST.toProblemReportDescription(),
                    thread = Thread(presentationRequestMessage.id)
                )

                MessageSender.packAndSendMessage(
                    Message(Json.encodeToString(problemReport)),
                    connection,
                    walletConnector,
                    transport,
                    services
                )

                //TODO: Here and everywhere catch all exceptions coming from user callbacks
                credPresenterController.onProblemReportGenerated(connection, problemReport)

            } catch (e: Throwable) {

                val problemReport = ProblemReport(
                    id = uuid4().toString(),
                    description = DidCommProblemCodes.INTERNAL_AGENT_ERROR.toProblemReportDescription(),
                    thread = Thread(presentationRequestMessage.id)
                )

                MessageSender.packAndSendMessage(
                    Message(Json.encodeToString(problemReport)),
                    connection,
                    walletConnector,
                    transport,
                    services
                )

                credPresenterController.onProblemReportGenerated(connection, problemReport)
            }

        } else {
            //TODO: Consider an option to send Send PresentationProposal here instead of ProblemReport, depending on user input

            val problemReport = ProblemReport(
                id = uuid4().toString(),
                description = DidCommProblemCodes.USER_REJECTED_PRESENTATION_REQUEST.toProblemReportDescription(),
                thread = Thread(presentationRequestMessage.id)
            )


            MessageSender.packAndSendMessage(
                Message(Json.encodeToString(problemReport)),
                connection,
                walletConnector,
                transport,
                services
            )


        }

        return ActionResult()

    }


}

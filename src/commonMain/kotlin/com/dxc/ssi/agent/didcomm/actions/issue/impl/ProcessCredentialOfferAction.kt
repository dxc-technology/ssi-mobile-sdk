package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.common.Attach
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceState
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.messages.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProcessCredentialOfferAction(
    private val actionParams: ActionParams,
    private val credentialOfferContainer: CredentialOfferContainer,
    private val offerResponseAction: OfferResponseAction? = null
) : CredentialIssuenceAction {
    private val logger: Kermit = Kermit(LogcatLogger())
    override suspend fun perform(): ActionResult {

        val walletConnector = actionParams.walletConnector
        val transport = actionParams.transport
        val services = actionParams.services
        //TODO: here and in all related places instead of NPE print relevant log message
        val credReceiverController = actionParams.callbacks.credReceiverController!!

        val existingCredentialExchangeRecord =
            walletConnector.prover!!.getCredentialExchangeRecordByThread(Thread(thid = credentialOfferContainer.id))
        if (existingCredentialExchangeRecord?.state != CredentialIssuenceState.OFFER_RECEIVED) throw IllegalStateException()

        val connection =
            walletConnector.walletHolder.getConnectionRecordById(existingCredentialExchangeRecord.connectionId)!!

        //TODO: deal with more than one message in attach
        val credentialOffer =
            walletConnector.prover.buildCredentialOfferObjectFromRawData(credentialOfferContainer.offersAttach[0].data)

        val credentialDefinition = existingCredentialExchangeRecord.credentialDefinition

        val actualOfferResponseAction = offerResponseAction
            ?: credReceiverController.onOfferReceived(
                connection = connection,
                credentialOfferContainer = credentialOfferContainer,
            )

        when (actualOfferResponseAction) {
            OfferResponseAction.ACCEPT -> {
                val credentialRequestInfo = walletConnector.prover!!.createCredentialRequest(
                    //TODO: check if it is OKay simply to take did of wallet holder always
                    proverDid = walletConnector.walletHolder.getIdentityDetails().did,
                    credentialDefinition = credentialDefinition,
                    credentialOffer = credentialOffer,
                    masterSecretId = Configuration.masterSecretId

                )

                val credentialRequest = CredentialRequestContainer(
                    id = uuid4().toString(),
                    thread = Thread(thid = credentialOfferContainer.id),
                    requestsAttach = listOf(
                        Attach(
                            id = "libindy-cred-request-0",
                            mimeType = "application/json",
                            data = walletConnector.prover!!.extractCredentialRequestDataFromCredentialInfo(
                                credentialRequestInfo
                            )

                        )
                    ),
                    //TODO: think if we need to set some meaningful comment here
                    comment = "comment"
                )

                logger.log(Severity.Debug,"",null) { "Credential request created:$credentialRequest" }

                MessageSender.packAndSendMessage(
                    Message(Json.encodeToString(credentialRequest)),
                    connection,
                    walletConnector,
                    transport,
                    services,
                    onMessageSent = {
                        walletConnector.prover.storeCredentialExchangeRecord(
                            CredentialExchangeRecord(
                                state = CredentialIssuenceState.REQUEST_SENT,
                                connectionId = connection.id,
                                //TODO: decide what data structure to store there: DiDComm generic offer or indy specific one
                                credentialOfferContainer = credentialOfferContainer,
                                credentialRequestContainer = credentialRequest,
                                credentialRequestInfo = credentialRequestInfo,
                                credentialDefinition = credentialDefinition,
                                thread = Thread(thid = credentialOfferContainer.id)
                            )
                        )

                        credReceiverController.onRequestSent(
                            connection = connection,
                            credentialRequestContainer = credentialRequest
                        )
                        null
                    },
                    onMessageSendingFailure = {
                        val problemReport = ProblemReport(
                            id = uuid4().toString(),
                            description = DidCommProblemCodes.COULD_NOT_SEND_CREDENTIAL_REQUEST.toProblemReportDescription()
                        )

                        //TODO: make this callback async
                        credReceiverController.onProblemReport(connection, problemReport)
                        null
                    }
                )
            }
            OfferResponseAction.REJECT -> {
                val problemReport = ProblemReport(
                    id = uuid4().toString(),
                    description = DidCommProblemCodes.COULD_NOT_SEND_CREDENTIAL_REQUEST.toProblemReportDescription()
                )
            }
            OfferResponseAction.PARK -> {
                walletConnector.prover.storeCredentialExchangeRecord(
                    //TODO: allow to store only changed fields
                    CredentialExchangeRecord(
                        state = CredentialIssuenceState.OFFER_RECEIVED,
                        connectionId = connection.id,
                        //TODO: decide what data structure to store there: DiDComm generic offer or indy specific one
                        credentialOfferContainer = credentialOfferContainer,
                        credentialDefinition = credentialDefinition,
                        thread = Thread(thid = credentialOfferContainer.id),
                        isParked = true
                    )
                )
            }
        }

        return ActionResult()
    }

}

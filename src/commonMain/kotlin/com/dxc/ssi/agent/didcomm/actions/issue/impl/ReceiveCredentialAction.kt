package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.commoon.MessageSender
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.ack.Ack
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceState
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredential
import com.dxc.utils.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ReceiveCredentialAction(
    private val actionParams: ActionParams
) : CredentialIssuenceAction {
    override suspend fun perform(): ActionResult {

        val walletConnector = actionParams.walletConnector
        val credReceiverController = actionParams.callbacks.credReceiverController!!
        val connection = actionParams.context?.connection!!
        val transport = actionParams.transport
        val services = actionParams.services
        val callbacks = actionParams.callbacks

        val credentialContainerMessage =
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<CredentialContainer>(actionParams.context.receivedUnpackedMessage!!.message)


        // 1. Check current state

        val existingCredentialExchangeRecord =
            walletConnector.prover!!.getCredentialExchangeRecordByThread(credentialContainerMessage.thread)
        if (existingCredentialExchangeRecord!!.state != CredentialIssuenceState.REQUEST_SENT) throw IllegalStateException()
        // 2. Execute callback
        if (credReceiverController.onCredentialReceived(
                connection = connection, credentialContainer = credentialContainerMessage
            ).canProceedFurther
        ) {

            val credential =
                walletConnector.prover!!.buildCredentialObjectFromRawData(
                    //TODO: deal with several attachemnts
                    credentialContainerMessage.credentialsAttach[0].data
                )

            // 3. Store credential
            walletConnector.prover.receiveCredential(
                credential = credential,
                credentialRequestInfo = existingCredentialExchangeRecord.credentialRequestInfo!!,
                credentialDefinition = existingCredentialExchangeRecord.credentialDefinition,
                //TODO: support revokation here
                revocationRegistryDefinition = null
            )
            // 4. Build Credential Ack
            val idRow = (credential as IndyCredential)
            val credentialAck = Ack(id = uuid4().toString(), thread =
            Thread(credentialContainerMessage.thread.thid), status = "OK",type = """did:sov:${
                idRow.schemaIdRaw.split(
                    ":"
                )[0]
            };spec/issue-credential/1.0/ack"""
            )
            // 5.  Send credential ack

            val result =  MessageSender.packAndSendMessage(
                Message(Json.encodeToString(credentialAck)),
                connection,
                walletConnector,
                transport,
                services,
                onMessageSent = {
                    services.connectionsTrackerService!!.trustPingSentOverConnectionEvent(connection)
                    callbacks.credReceiverController?.onAckSent(connection, credentialAck)
                    Result.Success(ActionResult())

                },
                onMessageSendingFailure = {
                    val problemReport = ProblemReport(
                        id = uuid4().toString(),
                        description = com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes.COULD_NOT_DELIVER_MESSAGE.toProblemReportDescription()
                    )
                    Result.Success(ActionResult())
                }
            )

            //Currently we proceed to finish the creential flow on agent side even if Ack was not delivered
            // 6. Remove credential exchange record
            walletConnector.prover.removeCredentialExchangeRecordByThread(credentialContainerMessage.thread)

            // 6. Execute callback
            credReceiverController.onDone(
                connection = connection, credentialContainer = credentialContainerMessage
            )
        } else {
            // TODO: Do not send ack / send problem report?
            // TODO: execute some problem related callback?
        }

        //TODO: return some meaningful ActionResult here and everywhere else
        return ActionResult()
    }

}

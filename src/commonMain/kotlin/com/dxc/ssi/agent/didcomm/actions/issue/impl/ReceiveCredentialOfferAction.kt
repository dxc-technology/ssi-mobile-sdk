package com.dxc.ssi.agent.didcomm.actions.issue.impl

import com.dxc.ssi.agent.didcomm.actions.ActionParams
import com.dxc.ssi.agent.didcomm.actions.ActionResult
import com.dxc.ssi.agent.didcomm.actions.issue.CredentialIssuenceAction
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceState
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
class ReceiveCredentialOfferAction(
    private val actionParams: ActionParams
) : CredentialIssuenceAction {
    var logger: Kermit = Kermit(LogcatLogger())
    override suspend fun perform(): ActionResult {
        try {

            logger.log(Severity.Debug,"",null) { "Entered ReceiveCredentialOfferAction" }

            val walletConnector = actionParams.walletConnector
            val ledgerConnector = actionParams.ledgerConnector

            val credentialOfferContainerMessage =
                Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<CredentialOfferContainer>(actionParams.context!!.receivedUnpackedMessage!!.message)
            val connection = actionParams.context.connection!!


            // TODO: Check current state (if there is existing record in a wallet for previous proposals/offers)
            // TODO: Improve: This is rudimentory validation check. Just checking that there is no such record in a wallet
            val existingCredentialExchangeRecord =
                walletConnector.prover!!.getCredentialExchangeRecordByThread(Thread(thid = credentialOfferContainerMessage.id))
            if (existingCredentialExchangeRecord != null) throw IllegalStateException()

            //TODO: deal with more than one message in attach
            val credentialOffer =
                walletConnector.prover!!.buildCredentialOfferObjectFromRawData(credentialOfferContainerMessage.offersAttach[0].data)

            val credentialDefinitionId =
                walletConnector.prover!!.createCredentialDefinitionIdFromOffer(credentialOffer)

            //TODO:handle null pointer here
            val credentialDefinition = ledgerConnector.retrieveCredentialDefinition(credentialDefinitionId)!!

            walletConnector.prover.storeCredentialExchangeRecord(
                CredentialExchangeRecord(
                    state = CredentialIssuenceState.OFFER_RECEIVED,
                    connectionId = connection.id,
                    //TODO: decide what data structure to store there: DiDComm generic offer or indy specific one
                    credentialOfferContainer = credentialOfferContainerMessage,
                    credentialDefinition = credentialDefinition,
                    thread = Thread(thid = credentialOfferContainerMessage.id)
                )
            )


            logger.log(Severity.Debug,"",null) { "Exited ReceiveCredentialOfferAction" }
            return ProcessCredentialOfferAction(actionParams, credentialOfferContainerMessage).perform()
        } catch (t: Throwable) {
            logger.log(Severity.Debug,"",null) { "Got exception ${t.stackTraceToString()}" }
            throw t
        }

    }

}

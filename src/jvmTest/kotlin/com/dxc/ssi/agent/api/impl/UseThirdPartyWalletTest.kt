package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.connection.ReconnectionError
import com.dxc.ssi.agent.api.callbacks.connection.StatefulConnectionController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.DidExchangeError
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.library.LibraryError
import com.dxc.ssi.agent.api.callbacks.library.LibraryStateListener
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.pluggable.wallet.WalletManager
import com.dxc.ssi.agent.api.pluggable.wallet.indy.IndyWalletConnector
import com.dxc.ssi.agent.didcomm.model.ack.Ack
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorBuilder
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PresentationRequestResponseAction
import com.dxc.ssi.agent.wallet.indy.IndyWalletHolder
import com.dxc.ssi.agent.wallet.indy.IndyWalletManager
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.Sleeper
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue


/*
*
* {
    "verificationTimeoutSec": "30",
    "attributes": [
        {
            "name": "all",
            "attribute": {
                "name": null,
                "names": [
                    "Dose Quantity",
                    "Vaccine Name",
                    "Dose Units"
                ],
                "restrictions": [
                    {
                        "Dose Units": "ml"
                    }
                ]
            }
        }
    ]
}
*
* */

class UseThirdPartyWalletTest {

   // private val walletName = "m1pIu6MS"
   // private val walletPassword =
    //    "T5+rPp/jk/ZgVDiyFBmX3+gdvUYno7pL6ryA1CbX+TTcxFKB3K9OBdDkoUC6qXpBqKVh/a5I9PJBeNO8H5/L+zIjucrYm/2872BdmsaFm3UOM1DmTF2rBtzWRLWZ6tXvWYEnmeHiJ8whbENI029DE8dNmrIcf8i6LLewnGkp96c="
    //   private val did = "Aj4mwDVVEh46K17Cqh4dpU"

    private val walletName = "RSMfGqFo"
    private val walletPassword = "qmsC9WLlalhAiQst0anTm2GEF1pZKmGmid6Gan7O/1LDByc+ZBy/h3Nf8dvrFVPJKl1z8EoJ4np754zsaBRSzkHmt2wcLGUyKtreLAB4o+kUEIw4SanJ0709LuyadhW4TkEAml0ALgLHuuRVvzjNw27gfACEn7KZhfmRPM6wSQw="


    private lateinit var ssiAgentApi: SsiAgentApi
    var logger: Kermit = Kermit(LogcatLogger())

    @Test
    //@Ignore("Ignored because it is actually integration tests which should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun openThirdPartyWalletTest() {
        logger.log(Severity.Debug, "", null) { "Starting test" }

        EnvironmentUtils.initEnvironment(EnvironmentImpl())

        val walletManager: WalletManager = IndyWalletManager

        if (walletManager.isWalletExistsAndOpenable(walletName, walletPassword)) {
            logger.i { "Wallet opened" }
        } else {
            logger.e { "Wallet could not be opened" }
            assertTrue { false }
        }

        val allMyDidsWithMeta = walletManager.getAllMyDidsWithMeta(walletName, walletPassword)

        if(allMyDidsWithMeta.isEmpty()) {
            logger.e { "Got no DID from the wallet. What to do?" }
            assertTrue { false }
        }

        allMyDidsWithMeta.forEach {
            logger.i { "Retrieved DID -> $it" }
        }

        if(allMyDidsWithMeta.size > 1) {
            logger.e { "Got several DIDs. Let's take first" }

        }

        val did = allMyDidsWithMeta.first().did

        if (!walletManager.isDidExistsInWallet(did, walletName, walletPassword)) {
            logger.e { "Could not use provided did" }
        }

        val walletHolder = IndyWalletHolder(
            walletName = walletName,
            walletPassword = walletPassword,
            didConfig = DidConfig(did = did)
        )

        val indyWalletConnector = IndyWalletConnector.build(walletHolder)


        val indyLedgerConnector = IndyLedgerConnectorBuilder()
            .withGenesisFilePath("/home/ivan/IdeaProjects/dxc/Lumedic/ssi-mobile-sdk/files/sovrin_stagingnet_genesis.txt")
            .build()

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withStatefulConnectionController(StatefulConnectionControllerImpl())
            .withLedgerConnector(indyLedgerConnector)
            .build()

        val invitationUrl =
            "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiM0htYU5pbW9UeWlXaktFZnJQSlRDMjJWdnZta1pzYloyZjlVWXIxcGlaZkIiXSwiQGlkIjoiYjFlN2RmZGQtY2YxNC00MTE3LWIyY2QtZTZkMTY2NjI2YzkwIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"



        ssiAgentApi.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                logger.i { "Initialized" }

                val existingCredInfos = ssiAgentApi.getCredentialInfos()

                existingCredInfos.forEach {
                    logger.i { "Got credential: $it" }
                }

                logger.log(Severity.Debug,"",null) { "Connecting to issuer" }

                val connection = ssiAgentApi.connect(invitationUrl, keepConnectionAlive = true)

                logger.log(Severity.Debug,"",null) { "Connected to issuer" }

            }

            override fun initializationFailed(
                error: LibraryError,
                message: String?,
                details: String?,
                stackTrace: String?
            ) {
                logger.log(Severity.Debug,"",null) { "Received error from library: $error with details: $details" }
            }

        })



        Sleeper().sleep(1_000_000)

    }

    class StatefulConnectionControllerImpl : StatefulConnectionController {
        var logger: Kermit = Kermit(LogcatLogger())
        override fun onReconnected(connection: PeerConnection) {
            TODO("Not yet implemented")
        }

        override fun onReconnectFailed(reconnectionError: ReconnectionError, reason: String?) {
            logger.log(Severity.Debug, "", null) { "Failed to reconnect: $reconnectionError " }
        }

        override fun onDisconnected(connection: PeerConnection) {
            TODO("Not yet implemented")
        }

    }

    inner class CredPresenterControllerImpl : CredPresenterController {
        override fun onRequestReceived(
            connection: PeerConnection,
            presentationRequest: PresentationRequestContainer
        ): PresentationRequestResponseAction {


            return PresentationRequestResponseAction.ACCEPT
        }

        override fun onDone(connection: PeerConnection) {

        }

        override fun onProblemReportGenerated(connection: PeerConnection, problemReport: ProblemReport) {

        }

    }

    inner class CredReceiverControllerImpl : CredReceiverController {
        override fun onOfferReceived(
            connection: PeerConnection,
            credentialOfferContainer: CredentialOfferContainer
        ): OfferResponseAction {

            return OfferResponseAction.ACCEPT
        }

        override fun onRequestSent(
            connection: PeerConnection,
            credentialRequestContainer: CredentialRequestContainer
        ) {

        }

        override fun onCredentialReceived(
            connection: PeerConnection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(connection: PeerConnection, credentialContainer: CredentialContainer) {

        }

        override fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult {
            TODO("Not yet implemented")
        }

        override fun onAckSent(connection: PeerConnection, ack: Ack) {
            logger.log(Severity.Debug, "", null) { "Ack sent for credential" }
        }


    }

    inner class ConnectionInitiatorControllerImpl() : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: PeerConnection,
            invitation: Invitation
        ): CallbackResult {
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(connection: PeerConnection, request: ConnectionRequest) {
            logger.log(Severity.Debug, "", null) { "Request sent hook called : $connection, $request" }
        }

        override fun onResponseReceived(connection: PeerConnection, response: ConnectionResponse): CallbackResult {
            logger.log(Severity.Debug, "", null) { "Response received hook called : $connection, $response" }
            return CallbackResult(true)
        }

        override fun onCompleted(connection: PeerConnection) {

        }

        override fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?) {

        }

        override fun onFailure(
            connection: PeerConnection?,
            error: DidExchangeError,
            message: String?,
            details: String?,
            stackTrace: String?
        ) {
            logger.log(
                Severity.Debug,
                "",
                null
            ) { "Failure occured for connection $connection, error-> $error, details -> $details" }
        }

    }
}
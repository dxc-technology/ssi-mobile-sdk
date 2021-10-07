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
import com.dxc.ssi.agent.api.pluggable.wallet.WalletCreationStrategy
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
import com.dxc.ssi.agent.wallet.indy.model.verify.IndyCredInfo
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.Sleeper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Ignore
import org.junit.Test

class SsiAgentApiImplTest {

    private val walletName = "newWalletName55"
    private val walletPassword = "newWalletPassword"
    private val did = "Aj4mwDVVEh46K17Cqh4dpU"

    private lateinit var ssiAgentApi: SsiAgentApi
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    //@Ignore("Ignored because it is actually integration tests which should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {
        logger.d { "Starting test" }

        EnvironmentUtils.initEnvironment(EnvironmentImpl())

        val walletManager: WalletManager = IndyWalletManager

        if (!walletManager.isWalletExistsAndOpenable(walletName, walletPassword))
            walletManager.createWallet(walletName, walletPassword, WalletCreationStrategy.CreateOrFail)

        if (!walletManager.isDidExistsInWallet(did, walletName, walletPassword)) {
            val didResult = walletManager.createDid(
                didConfig = DidConfig(did = did),
                walletName = walletName,
                walletPassword = walletPassword
            )
            logger.d { "Got generated didResult: did = ${didResult.did} , verkey = ${didResult.verkey}"}
            //Store did somewhere in your application to use it afterwards
        }

        val walletHolder = IndyWalletHolder(
            walletName = walletName,
            walletPassword = walletPassword,
            didConfig = DidConfig(did = did)
        )

        val indyWalletConnector = IndyWalletConnector.build(walletHolder)


        val indyLedgerConnector = IndyLedgerConnectorBuilder()
            .withGenesisFilePath("/home/ivan/IdeaProjects/dxc/Lumedic/ssi-mobile-sdk/files/sovrin_buildernet_genesis.txt")
            .build()

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withStatefulConnectionController(StatefulConnectionControllerImpl())
            .withLedgerConnector(indyLedgerConnector)
            .build()

        val invitationUrl =
            "ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNWQ4NGRkY2ItNjY3OC00YTE5LWI3NGQtOGVkYjZiNWQ2ZTFjIiwgInJlY2lwaWVudEtleXMiOiBbIjY5Z0FwSzVxTXRnMmFUcmhIN1hDeHVGc0Z3cGhrbVZ6ZlNwUENqVmJCNjhjIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiYTJlYzQ2NmEtZWUyYS00OGQxLTk4ZGMtZTdmOWZkMDFmZDI2IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyI5SlFWdkZLSzRneE01SHdlazJveWJjVXF6ZlpCZGV2NWRqNDFUUVc3c3FxYyJdLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiMjExZjU1ZjYtMjQ2ZC00MWM5LWE4OWYtYzU1OTliNzEyZWQwIiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyI2U3N0OEczd281YkZEMXZVU2F6NFVOYUJ3d2JUM1BnS0tnV21lTUFiZ1c5UyJdLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiZGYyZTk3N2UtMWZiNS00ZmM3LWI0MzAtYzY1MDcxZjY1OWViIiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJGaGFrcUc4cEF3UjR3eW1YOExGd2JxaXpGUDlaODJ1bjYzcFN5dE05RDhpOSJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNmRhOGQ2NzgtM2QwNi00ZWJjLWFlOTgtMTQ0MTg4MjBiMDU3IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJsYWJlbCI6ICJhbGljZS5hZ2VudCIsICJyZWNpcGllbnRLZXlzIjogWyJEUkxUb3I0aWpDNzRuNXlqTk5QS2hyQ0hXS0JDSkJXUm1ycm5wQ0NlRlRTeSJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiZmM5ZGZhZWEtNDIwNy00ODU2LWExNTMtNzNjODNkYzdiYTA2IiwgInJlY2lwaWVudEtleXMiOiBbIjZ2UHVZdFpVV2l2Vzc0WERNYzhad1lRRGVDZ3l2YTk1U1pDQnVKTkdYSnpIIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNzRiNmQ5M2ItM2M4My00NjM4LTgyYzEtNGFmYjk1NWRhZDk0IiwgInJlY2lwaWVudEtleXMiOiBbIjhCV1VtN283R1V1Y2F3aVAxTlFkZUJXVTROMThjZW01aHlIZGZRa3Jxdmt1Il0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiSkJFTjRrQlZpV0pSRmV1M29ncFEyaTNTQnlFWDE5Mk5Mbnl3TlV4ejRIUk4iXSwiQGlkIjoiNDU2ZWMwNmEtYmJiMi00NmJmLThjYzctMGM1YmVkZmJlNTNiIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"



        ssiAgentApi.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                logger.d { "Connecting to issuer" }
               // ssiAgentApi.abandonAllConnections()
                val connection = ssiAgentApi.connect(invitationUrl, keepConnectionAlive = true)

                logger.d { "Connected to issuer" }

            }

            override fun initializationFailed(
                error: LibraryError,
                message: String?,
                details: String?,
                stackTrace: String?
            ) {
                logger.d { "Received error from library: $error with details: $details" }
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
            logger.d { "Failed to reconnect: $reconnectionError " }
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
            logger.d { "Ack sent for credential" }
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
            logger.d { "Request sent hook called : $connection, $request" }
        }

        override fun onResponseReceived(connection: PeerConnection, response: ConnectionResponse): CallbackResult {
            logger.d { "Response received hook called : $connection, $response" }
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
            logger.d { "Failure occured for connection $connection, error-> $error, details -> $details" }
        }

    }
}
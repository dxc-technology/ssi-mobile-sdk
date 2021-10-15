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
import com.dxc.ssi.agent.ledger.indy.GenesisMode
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorBuilder
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.OfferResponseAction
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PresentationRequestResponseAction
import com.dxc.ssi.agent.wallet.indy.IndyWalletHolder
import com.dxc.ssi.agent.wallet.indy.IndyWalletManager
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.Sleeper

import org.junit.Test


class SsiAgentApiImplTest {

    private val walletName = "newWalletName70"
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
            .withGenesisFilePath("/Users/kkamyczek/krzysztof/ssi-mobile-sdk/files/sovrin_buildernet_genesis.txt")
            .withGenesisMode(GenesisMode.SOVRIN_BUILDERNET)
            .build()

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withStatefulConnectionController(StatefulConnectionControllerImpl())
            .withLedgerConnector(indyLedgerConnector)
            .build()

        val invitationUrl=
            "ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiODQxNzYyM2MtZWUwZC00NWJmLTg0MGQtNzIwODJhMzVhMWY3IiwgInJlY2lwaWVudEtleXMiOiBbIkRhZ0V0ZHA0c3RyWVk0V1hHTlNmWFFoQ1V2alc2V014VTE5RTd3dlRwN3E1Il0sICJsYWJlbCI6ICJhbGljZS5hZ2VudCIsICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiN2FmY2RkYTUtMWQzOC00YjU4LTgyMWYtNWI1ZWFiMzU4ZDI2IiwgInJlY2lwaWVudEtleXMiOiBbIkJLd0NydWlxRXQxMndMaVBvWXlvNm5aNlc2MjdIeWV1VTFvUXRKYm1lTmlQIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiODAwMDc1OTYtYTBlYS00OTFkLWEzNTYtN2MyYzZmODViMTM2IiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJFRThtOVliSm9meFBWZFphRFRlOFQ2b0JGTWJvdDFYeEJWUDltNWtkWldBNiJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiODZmZTU1ZGUtOGVhNC00M2YzLTkzNjUtNDk2ZmRlNTI2MzRjIiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInJlY2lwaWVudEtleXMiOiBbIjg1Sk44Y3Bmb2hrakx0RllocEd3VkFwRHoxdHZ2dlhzbXJwQktrR1dUdWNFIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiM2IxMGE5ZDAtODIxNi00MTQ4LTlkNmYtNTY5NDU4ZjM1ZDg1IiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyIzRm9EVTQ5UGhyTmh2VU1SZnFTdVg1dG1wNWt5dnV3OFJmQm1KUDIzbWZjUCJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiODg4MDVhMjQtZDhkNC00YTg3LWFiZTgtZjUwYmRmMTIzZDgyIiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJBTktwRzNrc1hNNjVXSFNIcHNmYkVNOE1TNjF2SHN4SDRON3JIYzgxR0tSbyJdLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNTZkMDRmYWItNWZmZi00N2YyLWFlOGQtZDNhYWRkMTMyYWFmIiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyIzdFNDN3FtOFd2SzhFa3E3bktBRUR3VGgxRzJXaFBYbTl0cnBweFBCcWg3cSJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiMzM4NWYwYmEtYjU3Ny00ZTQ5LWI2ZmMtZGJmNTNkNzMxNzRmIiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJsYWJlbCI6ICJhbGljZS5hZ2VudCIsICJyZWNpcGllbnRLZXlzIjogWyJKREtzM2YyUmhpS3J0Y2lMNWd2N2hFYVZlaHlGbm15cTVUd2Q3MndVdHN4Il19"
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiYTAwZTYyYWYtNWU2NC00MWVhLTg4ZDgtOTZlNjEwY2QyOGJjIiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJDU0NTdU5MNGVUTVJtckVSTlJxdFhoOUhiM2s1SGRUQVd0aFhzVnFFSmVxdSJdfQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiY2JmYzk3ZTAtYTJlOC00ZjBiLTlkYzktMzU4MDlmMGY0Y2JiIiwgInJlY2lwaWVudEtleXMiOiBbIkZqWHB1c0NmWFR2ZGdIQ1Ruc1haUVJNYTY1YXd0YzFHd2N1RnJOaThjWmtIIl0sICJsYWJlbCI6ICJhbGljZS5hZ2VudCIsICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiOTk1YTBjMzgtNjEwOC00MWJlLWE4ZGUtZDJjNzk5OWY2NzllIiwgInJlY2lwaWVudEtleXMiOiBbIkQ1S2hxcHZZSm1lV0EzQlpIVVJpU0NlVGhKWmtERXpxb2hBQWlWRDdITHhLIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiN2Q1ODQ2NGEtZTQ5MS00NDMxLTllZGYtODg4YzBhNDJiMGRlIiwgInJlY2lwaWVudEtleXMiOiBbIjRDR2QxcXhwVVhCcm5IdWhUb0NyajNCdkJodHhDRWpCRDJTc1VodFZ1WUNDIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiZjg5ZTBjMWUtZWI3Zi00NDczLTg4M2EtYTJlZDBjZWEwYzcwIiwgImxhYmVsIjogImFsaWNlLmFnZW50IiwgInJlY2lwaWVudEtleXMiOiBbIkMyN0hSN2VwR2p2cm1qcHNyYlZtSEQydlQ1ajNjWHJTeTh1Rkw2aTVhTkRmIl0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNGFmYmM5ZGItYzdhNi00ODBhLThmMTItZDdmMTdjNzc3MGZiIiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJBNXJWQ0tlZFloYVk0VUxtUjRVSDduVHlFc3FtZzhyc1p6cjU2YldEck05RSJdLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiODQ4OTU0MTItZWZlMC00NTViLWJlMjItZmI2YTBmYmZiNzRmIiwgInNlcnZpY2VFbmRwb2ludCI6ICJ3czovLzE5Mi4xNjguMC4xMDQ6ODAzMCIsICJyZWNpcGllbnRLZXlzIjogWyJFNGhKNXh3NEdKM3RNZlF2RHB2MjRtUU5xZmRSVkJpdGREN3lWSGtiQmlRSiJdLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="
            //"ws://192.168.0.104:8030?c_i=eyJAdHlwZSI6ICJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiIsICJAaWQiOiAiNzMwMWJiZjItNTJmZS00ZjQyLTkzMzMtNDRkMzNhM2Q1MDkxIiwgInJlY2lwaWVudEtleXMiOiBbIjNRVkpkbVY4ekR0cjh2UEVvQkdjNTRyYlZKVkZYa0hxa29GWlczYlliRUQ3Il0sICJzZXJ2aWNlRW5kcG9pbnQiOiAid3M6Ly8xOTIuMTY4LjAuMTA0OjgwMzAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQifQ=="

        ssiAgentApi.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                logger.d ("Control"){ "Connecting to issuer" }
                ssiAgentApi.connect(invitationUrl, keepConnectionAlive = true)
                logger.d ("Control"){ "Connected to issuer" }

            }

            override fun initializationFailed(
                error: LibraryError,
                message: String?,
                details: String?,
                stackTrace: String?
            ) {
                logger.d ("Control"){ "Received error from library: $error with details: $details" }
            }

        })



        Sleeper().sleep(1_000_000_000)

    }

    class StatefulConnectionControllerImpl : StatefulConnectionController {
        var logger: Kermit = Kermit(LogcatLogger())
        override fun onReconnected(connection: PeerConnection) {
            // TODO("Not yet implemented")
            logger.d ("Control"){ "onReconnected" }
        }

        override fun onReconnectFailed(reconnectionError: ReconnectionError, reason: String?) {
            logger.d ("Control"){ "Failed to reconnect: $reconnectionError " }
        }

        override fun onDisconnected(connection: PeerConnection) {
            logger.d ("Control"){ "onDisconnected" }
            //TODO("Not yet implemented")
        }

    }

    inner class CredPresenterControllerImpl : CredPresenterController {
        override fun onRequestReceived(
            connection: PeerConnection,
            presentationRequest: PresentationRequestContainer
        ): PresentationRequestResponseAction {
            logger.d ("Control"){ "CredPresenterControllerImpl ACCEPT" }
            return PresentationRequestResponseAction.ACCEPT
        }

        override fun onDone(connection: PeerConnection) {
            logger.d ("Control"){ "CredPresenterControllerImpl onDone" }
        }

        override fun onProblemReportGenerated(connection: PeerConnection, problemReport: ProblemReport) {
            logger.d ("Control"){ "CredPresenterControllerImpl onProblemReportGenerated" }
        }

    }

    inner class CredReceiverControllerImpl : CredReceiverController {
        override fun onOfferReceived(
            connection: PeerConnection,
            credentialOfferContainer: CredentialOfferContainer
        ): OfferResponseAction {

            logger.d ("Control"){ "OfferResponseAction.ACCEPT" }
            return OfferResponseAction.ACCEPT
        }

        override fun onRequestSent(
            connection: PeerConnection,
            credentialRequestContainer: CredentialRequestContainer
        ) {
            logger.d ("Control"){ "CredReceiverControllerImpl onRequestSent" }
        }

        override fun onCredentialReceived(
            connection: PeerConnection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            logger.d ("Control"){ "CredReceiverControllerImpl onCredentialReceived" }
            return CallbackResult(true)
        }

        override fun onDone(connection: PeerConnection, credentialContainer: CredentialContainer) {
            logger.d ("Control"){ "Ack sent for credential onDone" }
        }

        override fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult {
            TODO("Not yet implemented")
        }

        override fun onAckSent(connection: PeerConnection, ack: Ack) {
            logger.d ("Control") { "Ack sent for credential" }
        }
    }

    inner class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: PeerConnection,
            invitation: Invitation
        ): CallbackResult {
            logger.d ("Control") { "ConnectionInitiatorControllerImpl onInvitationReceived" }
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(connection: PeerConnection, request: ConnectionRequest) {
            logger.d ("Control"){ "Request sent hook called : $connection, $request" }
        }

        override fun onResponseReceived(connection: PeerConnection, response: ConnectionResponse): CallbackResult {
            logger.d ("Control"){ "Response received hook called : $connection, $response" }
            return CallbackResult(true)
        }

        override fun onCompleted(connection: PeerConnection) {
            logger.d ("Control") { "onCompleted : $connection" }
        }

        override fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?) {
            logger.d ("Control") { "onAbandoned : $connection" }
        }

        override fun onFailure(
            connection: PeerConnection?,
            error: DidExchangeError,
            message: String?,
            details: String?,
            stackTrace: String?
        ) {
            logger.d ("Control") { "Failure occured for connection $connection, error-> $error, details -> $details" }
        }

    }
}

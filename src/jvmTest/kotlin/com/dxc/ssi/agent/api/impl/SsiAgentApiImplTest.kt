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
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
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

    @Test
    @Ignore("Ignored because it is actually integration tests which should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {
        println("Starting test")

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
            print("Got generated didResult: did = ${didResult.did} , verkey = ${didResult.verkey}")
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

        val invitationUrl = "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiOVNFb1NpV2hZOEs4dmo0TUExYU1rNjJmQzhwaEQ5ZGFhYmRFbUN6VkF3QWgiXSwiQGlkIjoiNjJhNDJhNWYtZGY0Yi00OTJhLWI0YmMtMTk1OWVlYjkwYzkyIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"



        ssiAgentApi.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                println("Connecting to issuer")
                ssiAgentApi.abandonAllConnections()
                val connection = ssiAgentApi.connect(invitationUrl, keepConnectionAlive = true)

                println("Connected to issuer")

            }

            override fun initializationFailed(
                error: LibraryError,
                message: String?,
                details: String?,
                stackTrace: String?
            ) {
                println("Received error from library: $error with details: $details")
            }

        })



        Sleeper().sleep(1_000_000)

    }

    class StatefulConnectionControllerImpl : StatefulConnectionController {
        override fun onReconnected(connection: PeerConnection) {
            TODO("Not yet implemented")
        }

        override fun onReconnectFailed(reconnectionError: ReconnectionError, reason: String?) {
            println("Failed to reconnect: $reconnectionError ")
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

            GlobalScope.launch {
                delay(10_000)

                println("Woken up...")

                ssiAgentApi.getParkedPresentationRequests().forEach { presentationRequestContainer ->
                    println("Accepting parked presentation request $presentationRequestContainer")
                    ssiAgentApi.processParkedPresentationRequest(
                        presentationRequestContainer,
                        PresentationRequestResponseAction.ACCEPT
                    )
                }
            }

            return PresentationRequestResponseAction.PARK
        }

        override fun onDone(connection: PeerConnection): CallbackResult {
            return CallbackResult(true)
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
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onCredentialReceived(
            connection: PeerConnection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(connection: PeerConnection, credentialContainer: CredentialContainer): CallbackResult {
            GlobalScope.launch {
                delay(20_000)
                println("getting stored credentials infos form wallet")
                val credInfos = ssiAgentApi.getCredentialInfos().map { it as IndyCredInfo }


                println("retrieved set of cred infos with size ${credInfos.size} : $credInfos")

                println("retrieving one credential info by referrent Id: ${credInfos[0].referent}")

                val credInfo = ssiAgentApi.getCredentialInfo(credInfos[0].referent)

                println("Extracted credential: $credInfo")


            }
            return CallbackResult(true)
        }

        override fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult {
            TODO("Not yet implemented")
        }


    }

    inner class ConnectionInitiatorControllerImpl() : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: PeerConnection,
            invitation: Invitation
        ): CallbackResult {
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(connection: PeerConnection, request: ConnectionRequest): CallbackResult {
            println("Request sent hook called : $connection, $request")
            return CallbackResult(true)
        }

        override fun onResponseReceived(connection: PeerConnection, response: ConnectionResponse): CallbackResult {
            println("Response received hook called : $connection, $response")
            return CallbackResult(true)
        }

        override fun onCompleted(connection: PeerConnection): CallbackResult {
            println("Connection completed : $connection")
            GlobalScope.launch {
                delay(5000)
                ssiAgentApi.abandonConnection(connection)

            }
            return CallbackResult(true)
        }

        override fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?): CallbackResult {
            println("Received connection abandoned hook")
            GlobalScope.launch {
                delay(5000)
                ssiAgentApi.reconnect(connection)

            }
            return CallbackResult(true)
        }

        override fun onFailure(
            connection: PeerConnection?,
            error: DidExchangeError,
            message: String?,
            details: String?,
            stackTrace: String?
        ) {
            println("Failure occured for connection $connection, error-> $error, details -> $details")
        }

    }
}
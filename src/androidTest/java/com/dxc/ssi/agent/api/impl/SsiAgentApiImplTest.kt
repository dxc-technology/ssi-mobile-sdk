package com.dxc.ssi.agent.api.impl

import android.Manifest
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.library.LibraryStateListener
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

//TODO: if we can use some common kotlin tests to have common tests for all platforms
class SsiAgentApiImplTest {

    private val walletName = "newWalletName2"
    private val walletPassword = "newWalletPassword"
    private val did = "Goci8gnhuC9vvxTWg1aFSx"
    lateinit var ssiAgentApi: SsiAgentApi

    @Rule
    @JvmField
    val permissionRule = if (Build.VERSION.SDK_INT >= 30) {   //  Use Android API 30 Platform to run it
        GrantPermissionRule.grant(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            //  Instead of running this: Manifest.permission.MANAGE_EXTERNAL_STORAGE use this command: adb shell appops set --uid com.dxc.ssi.agent.test MANAGE_EXTERNAL_STORAGE allow
        )
    } else {
        GrantPermissionRule.grant(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
        )
    }

    @Test
    @Ignore("Ignored because it is actually integration tests which should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        println("Starting test")

        EnvironmentUtils.initEnvironment(EnvironmentImpl(instrumentation.context))

        val walletManager: WalletManager = IndyWalletManager

        if (!walletManager.isWalletExistsAndOpenable(walletName, walletPassword))
            walletManager.createWallet(walletName, walletPassword)

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

        val indyLedgerConnector = IndyLedgerConnectorBuilder()
            .withGenesisMode(GenesisMode.SOVRIN_BUILDERNET)
            .build()

        val indyWalletConnector = IndyWalletConnector.build(walletHolder)

        ssiAgentApi = SsiAgentBuilderImpl(indyWalletConnector)
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(indyLedgerConnector)
            .build()


        val issuerInvitationUrl =
            "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiMlBVdXY3dnpEQk16b2szQUFQRThHN2dlVk5KbUVlWTdTRGlOaUhRQTFhaDgiXSwiQGlkIjoiZDBhNjcyY2YtNDczOC00NDdlLWI3MWQtYjU4NTZmYTEzMTk1IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"


        ssiAgentApi.init(object : LibraryStateListener {
            override fun initializationCompleted() {

                ssiAgentApi.abandonAllConnections(force = true, notifyPeerBeforeAbandoning = false)


                println("Connecting to issuer")
                val connection = ssiAgentApi.connect(issuerInvitationUrl, keepConnectionAlive = true)
                println("Connected to issuer")

            }

            override fun initializationFailed() {
                TODO("Not yet implemented")
            }
        })

        Sleeper().sleep(800000)

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

            println("Received credential offer")

            GlobalScope.launch {
                delay(20_000)
                ssiAgentApi.getParkedCredentialOffers()
                    .forEach {
                        ssiAgentApi.processParkedCredentialOffer(it, OfferResponseAction.ACCEPT)
                    }

            }

            return OfferResponseAction.PARK
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
            return CallbackResult(true)
        }

        override fun onProblemReport(connection: PeerConnection, problemReport: ProblemReport): CallbackResult {
            TODO("Not yet implemented")
        }


    }

    class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
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
            return CallbackResult(true)
        }

        override fun onAbandoned(connection: PeerConnection, problemReport: ProblemReport?): CallbackResult {
            println("Connection completed : $connection")
            return CallbackResult(true)
        }


    }
}
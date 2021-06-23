package com.dxc.ssi.agent.api.impl

import android.Manifest
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.dxc.ssi.agent.api.callbacks.CallbackResult
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.didcomm.model.common.ProblemReport
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionRequest
import com.dxc.ssi.agent.didcomm.model.didexchange.ConnectionResponse
import com.dxc.ssi.agent.didcomm.model.didexchange.Invitation
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialOfferContainer
import com.dxc.ssi.agent.didcomm.model.issue.container.CredentialRequestContainer
import com.dxc.ssi.agent.didcomm.model.verify.container.PresentationRequestContainer
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.model.Connection
import com.dxc.utils.Sleeper
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

//TODO: if we can use some common kotlin tests to have common tests for all platforms
class SsiAgentApiImplTest {


    @Rule
    @JvmField
    val permissionRule = if (Build.VERSION.SDK_INT <= 30) {
        GrantPermissionRule.grant(
            //Manifest.permission.MANAGE_EXTERNAL_STORAGE, Use Android API 30 Platform to run it
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
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
    @Ignore("Ignored because it is actually integration tets whoch should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        println("Starting test")
        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
            ipAddress = "192.168.0.117")

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withEnvironment(EnvironmentImpl(instrumentation.context))
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(IndyLedgerConnector(indyLedgerConnectorConfiguration))
            .build()

        ssiAgentApi.init()


        val issuerInvitationUrl =
            "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkoxRVZQUVg0VzkyRjN0ajhUQVkzSEJGV3Myem85M29uVTliMWZ6OExiSzRhIl0sInJlY2lwaWVudEtleXMiOlsiNDZGdFlaVGRxajIyelNXYlR1WXd3R3U1dkJSWFl5UXR6TEsyUXdiQ2thZUsiXSwiQGlkIjoiZmVlMWIyNzUtNjQ0Ni00YjRlLWEwZjEtZDEyNDI0NzY3ZDYzIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"

        val verifierInvitationUrl =
            "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiRndORkdDOU1YdTNrOXhleFhQaDNOcHZaMkx3VVl2eDU2YnRzd0Z2MU0xc3MiXSwicmVjaXBpZW50S2V5cyI6WyJBc1BLNzZiMW1lWXVqWmI4aHhLdTV6RjlzamlIVE5Sa2tuakFSNnY3WmhFWSJdLCJAaWQiOiI0ZmQzYTBiZi1kZjNjLTQ1MTMtYTljMC05M2JkNmVjMmNlZDkiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="

        println("Connecting to issuer")
        ssiAgentApi.connect(issuerInvitationUrl)

        //TODO: ensure that connection can be established without delay between two connections
        //Sleeper().sleep(4000)

        println("Connecting to verifier")
        ssiAgentApi.connect(verifierInvitationUrl)

        Sleeper().sleep(500000)

    }

    class CredPresenterControllerImpl : CredPresenterController {
        override fun onRequestReceived(
            connection: Connection,
            presentationRequest: PresentationRequestContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(connection: Connection): CallbackResult {
            return CallbackResult(true)
        }

    }

    class CredReceiverControllerImpl : CredReceiverController {
        override fun onOfferReceived(
            connection: Connection,
            credentialOfferContainer: CredentialOfferContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onRequestSent(
            connection: Connection,
            credentialRequestContainer: CredentialRequestContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onCredentialReceived(
            connection: Connection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(
            connection: Connection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            return CallbackResult(true)
        }


    }

    class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: Connection,
            endpoint: String,
            invitation: Invitation
        ): CallbackResult {
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(
            connection: Connection,
            request: ConnectionRequest
        ): CallbackResult {
            println("Request sent hook called : $connection, $request")
            return CallbackResult(true)
        }

        override fun onResponseReceived(
            connection: Connection,
            response: ConnectionResponse
        ): CallbackResult {
            println("Response received hook called : $connection, $response")
            return CallbackResult(true)
        }

        override fun onCompleted(connection: Connection): CallbackResult {
            println("Connection completed : $connection")
            return CallbackResult(true)
        }

        override fun onAbandoned(
            connection: Connection,
            problemReport: ProblemReport
        ): CallbackResult {
            println("Connection abandoned : $connection")
            return CallbackResult(true)
        }

    }
}
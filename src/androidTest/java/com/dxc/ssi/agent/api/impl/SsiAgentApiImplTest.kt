package com.dxc.ssi.agent.api.impl

import android.Manifest
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
import com.dxc.ssi.agent.model.SharedConnection
import com.dxc.utils.Sleeper
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

//TODO: if we can use some common kotlin tests to have common tests for all platforms
class SsiAgentApiImplTest {

    @Rule
    @JvmField
    val permissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    @Test
    //@Ignore("Ignored because it is actually integration tets whoch should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        println("Starting test")
        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
            ipAddress = "192.168.0.116")

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withEnvironment(EnvironmentImpl(instrumentation.context))
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(IndyLedgerConnector(indyLedgerConnectorConfiguration))
            .build()

        ssiAgentApi.init()


        val issuerInvitationUrl =
            "ws://192.168.0.116:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTY6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkdSckFKQlp3WTJiQ0Q5WGZUQjc4MlNBWloyQW1TTTlBbmZ0SlpOYk5aQjdUIl0sInJlY2lwaWVudEtleXMiOlsiUlpSVDRyQnlDUk5XQUJkNU5FZkFnaGF6WUxXQTc4d3hONmlQWHg3ZzVvbSJdLCJAaWQiOiI4MGM2NTFiNy05YjlkLTQ1ZDktODNmMi1iZGRjOTQ4M2FhNmEiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
            val verifierInvitationUrl =
            "ws://192.168.0.116:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNjo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiSDlObkREVkRWbWh2bUdTNVJOQWdUVloyN3ozbjc5Mm5YWE5jUTlrak11U2IiXSwicmVjaXBpZW50S2V5cyI6WyJHTHRkWVh2aHlUellHcnFtYlZ4NU5CanhBUnBjRjRHOEpYRUJvbTMyYzNFdyJdLCJAaWQiOiIxN2ZkMzdhNC01MDhlLTRlZDMtODZiOC0wODBlZDI2OGVkYjkiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
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
            connection: SharedConnection,
            presentationRequest: PresentationRequestContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(connection: SharedConnection): CallbackResult {
            return CallbackResult(true)
        }

    }

    class CredReceiverControllerImpl : CredReceiverController {
        override fun onOfferReceived(
            connection: SharedConnection,
            credentialOfferContainer: CredentialOfferContainer
        ): CallbackResult {
            print ("CredentialReceiverControllerImpl:onCredentialReceived")
            print (connection)
            print ("CredentialReceiverControllerImpl:credentialOfferContainer:")
            print (credentialOfferContainer)
            return CallbackResult(true)
        }

        override fun onRequestSent(
            connection: SharedConnection,
            credentialRequestContainer: CredentialRequestContainer
        ): CallbackResult {
            print ("CredentialReceiverControllerImpl:onRequestSent")
            print (connection)
            print ("CredentialReceiverControllerImpl:credentialOfferContainer:")
            print (credentialRequestContainer)
            return CallbackResult(true)
        }

        override fun onCredentialReceived(
            connection: SharedConnection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            print ("CredentialReceiverControllerImpl:onCredentialReceived")
            print (connection)
            print ("CredentialReceiverControllerImpl:credentialContainer:")
            print (credentialContainer)
            return CallbackResult(true)
        }

        override fun onDone(connection: SharedConnection, credentialContainer: CredentialContainer): CallbackResult {
            print ("CredentialReceiverControllerImpl:onDone")
            print (connection)
            print ("CredentialReceiverControllerImpl:credentialContainer:")
            print (credentialContainer)
            return CallbackResult(true)
        }


    }

    class ConnectionInitiatorControllerImpl : ConnectionInitiatorController {
        override fun onInvitationReceived(
            connection: SharedConnection,
            endpoint: String,
            invitation: Invitation
        ): CallbackResult {
            return CallbackResult(canProceedFurther = true)
        }

        override fun onRequestSent(connection: SharedConnection, request: ConnectionRequest): CallbackResult {
            println("Request sent hook called : $connection, $request")
            return CallbackResult(true)
        }

        override fun onResponseReceived(connection: SharedConnection, response: ConnectionResponse): CallbackResult {
            println("Response received hook called : $connection, $response")
            return CallbackResult(true)
        }

        override fun onCompleted(connection: SharedConnection): CallbackResult {
            println("Connection completed : $connection")
            return CallbackResult(true)
        }

        override fun onAbandoned(connection: SharedConnection, problemReport: ProblemReport): CallbackResult {
            println("Connection abandoned : $connection")
            return CallbackResult(true)
        }

    }
}
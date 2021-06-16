package com.dxc.ssi.agent.api.impl

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
import org.junit.Test

class SsiAgentApiImplTest {

    @Test
    @Ignore("Ignored because it is actually integration tets whoch should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {

        println("Starting test")

        val ssiAgentApi = SsiAgentBuilderImpl()
            .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
            .withCredReceiverController(CredReceiverControllerImpl())
            .withCredPresenterController(CredPresenterControllerImpl())
            .withLedgerConnector(IndyLedgerConnector(IndyLedgerConnectorConfiguration(genesisFilePath = "/home/ifedyanin/source/github/fedyiv/ssi-mobile-sdk-lumedic/files/docker_pool_transactions_genesis.txt")))
            .withEnvironment(EnvironmentImpl())
            .build()

        ssiAgentApi.init()


        val issuerInvitationUrl =
            "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIlE2Ykx4cG1hTVNMOUVCVG9CV1BVdWtOVVBEUTNZVWJWdEZFZU5wbXA5NGMiXSwicmVjaXBpZW50S2V5cyI6WyIzelhpU2s2TXpLekw5R1RielhIVUZDZkhQQXl1RnA5NGZLZXVSQ0Z6RXVxYSJdLCJAaWQiOiI4MGIwOTJlYS0wZTY0LTQ5NWYtYjU0NC1mYjYxZTM3YmY1NjUiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="

        val verifierInvitationUrl =
            "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiR3dqR0pBQkZzRnNoZkFzYUpxNWFqOXVQdExHSmRDYXpFM2NiaWVvYWpoNVUiXSwicmVjaXBpZW50S2V5cyI6WyJzNlBOaUIzZlN6UG50V29aNXNmOHY4NDN6UjNyWEYxRmJXR1VtSEVIb2ZmIl0sIkBpZCI6IjlmMTI4YTI0LWM2YzMtNDhiMi1hN2Q1LTY4MzYyM2JmMmMwMSIsIkB0eXBlIjoiZGlkOnNvdjpCekNic05ZaE1yakhpcVpEVFVBU0hnO3NwZWMvY29ubmVjdGlvbnMvMS4wL2ludml0YXRpb24ifQ=="

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
            return CallbackResult(true)
        }

        override fun onRequestSent(
            connection: SharedConnection,
            credentialRequestContainer: CredentialRequestContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onCredentialReceived(
            connection: SharedConnection,
            credentialContainer: CredentialContainer
        ): CallbackResult {
            return CallbackResult(true)
        }

        override fun onDone(connection: SharedConnection, credentialContainer: CredentialContainer): CallbackResult {
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
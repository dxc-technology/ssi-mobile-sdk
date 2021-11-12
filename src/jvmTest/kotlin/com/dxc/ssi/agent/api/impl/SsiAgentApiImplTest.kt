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
import java.net.Inet4Address


class SsiAgentApiImplTest {

    companion object {
        val localIp: String = Inet4Address.getLocalHost().hostAddress
        const val port = 8124
    }
    private val walletName = "newWalletName78"
    private val walletPassword = "newWalletPassword"
    private val did = "Aj4mwDVVEh46K17Cqh4dpU"

    private lateinit var ssiAgentApi: SsiAgentApi
    var logger: Kermit = Kermit(LogcatLogger())
    @Test
    //@Ignore("Ignored because it is actually integration tests which should be moved out of unit tests in order to to run during build")
    //TODO: Move integration tests to separate module
    fun basicTest() {
        logger.d { "Starting test" }

        println(Inet4Address.getLocalHost().hostAddress)

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
            println(didResult)
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
            .build(localIp,port)

        val invitationUrl =
            "ws://192.168.0.103:8030?c_i=eyJAdHlwZSI6ICJodHRwczovL2RpZGNvbW0ub3JnL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIiwgIkBpZCI6ICI1ZGYyMmQwZS05ZmU3LTRiZDAtYTgwMy0xMTgxODljOGQzNjYiLCAic2VydmljZUVuZHBvaW50IjogIndzOi8vMTkyLjE2OC4wLjEwMzo4MDMwIiwgInJlY2lwaWVudEtleXMiOiBbIlQ0dG8zQVhqWGhaczRpYUI4QW1tdjRQdFd6QWVSY3JnS1V2b0pVa0JhOFciXSwgImxhYmVsIjogImFsaWNlLmFnZW50In0="
            //"ws://192.168.0.103:8030?c_i=eyJAdHlwZSI6ICJodHRwczovL2RpZGNvbW0ub3JnL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIiwgIkBpZCI6ICI4ZGZiNzE2Yi01NTNiLTQxNjUtOTE2Ny1jZjc5YzUxZjUxYjAiLCAibGFiZWwiOiAiYWxpY2UuYWdlbnQiLCAic2VydmljZUVuZHBvaW50IjogIndzOi8vMTkyLjE2OC4wLjEwMzo4MDMwIiwgInJlY2lwaWVudEtleXMiOiBbIkZGVlU0WTdWYUZydmJuOTVkZVlyaE12cHlLQ2lKeUxhb0VuRjNSYmNSc0hhIl19"
            //"ws://192.168.0.103:8030?c_i=eyJAdHlwZSI6ICJodHRwczovL2RpZGNvbW0ub3JnL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIiwgIkBpZCI6ICIxNjNjZTk0Mi1mMTRlLTRlMzItODk5Mi1jMzVhNmQ0ZjI0YjIiLCAic2VydmljZUVuZHBvaW50IjogIndzOi8vMTkyLjE2OC4wLjEwMzo4MDMwIiwgInJlY2lwaWVudEtleXMiOiBbIkdTRGdpRFk2UVJEWUhHYW1qUjJNTW5QTEhTYTFBVlNxcTRYeWJDWk4xSzlMIl0sICJsYWJlbCI6ICJhbGljZS5hZ2VudCJ9"

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

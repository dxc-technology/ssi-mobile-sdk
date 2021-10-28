package com.dxc.ssi.agent.api.impl

import co.touchlab.stately.freeze
import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.SsiAgentBuilder
import com.dxc.ssi.agent.api.callbacks.connection.StatefulConnectionController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionResponderController
import com.dxc.ssi.agent.api.callbacks.issue.CredIssuerController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.trustping.TrustPingController
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.callbacks.verification.CredVerifierController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.transport.WebSocketTransportImpl

class SsiAgentBuilderImpl(private val walletConnector: WalletConnector) : SsiAgentBuilder {

    var logger: Kermit = Kermit(LogcatLogger())
    private var transport: Transport? = null
    private var ledgerConnector: LedgerConnector? = null
    private var connectionInitiatorController: ConnectionInitiatorController? = null
    private var connectionResponderController: ConnectionResponderController? = null
    private var credReceiverController: CredReceiverController? = null
    private var credIssuerController: CredIssuerController? = null
    private var credPresenterController: CredPresenterController? = null
    private var credVerifierController: CredVerifierController? = null
    private var statefulConnectionController: StatefulConnectionController? = null
    private var trustPingController: TrustPingController? = null
    private lateinit var ip: String
    private var port: Int = 0

    override fun build(ip:String, port:Int): SsiAgentApi {
        this.ip = ip
        this.port = port
        var result: SsiAgentApiImpl? = null
        try {
            if (transport == null) {
                transport = WebSocketTransportImpl(ip,port)
            }

            if (ledgerConnector == null)
                ledgerConnector = IndyLedgerConnector(IndyLedgerConnectorConfiguration())

            //TODO: think about some sensible defaults for those callbacks
            val callbacks = Callbacks(
                connectionInitiatorController,
                connectionResponderController,
                credReceiverController,
                credIssuerController,
                credPresenterController,
                credVerifierController,
                statefulConnectionController,
                trustPingController
            )
            result = SsiAgentApiImpl(
                transport = transport!!,
                walletConnector = walletConnector!!,
                ledgerConnector = ledgerConnector!!,
                callbacks = callbacks,
                ip = ip,
                port = port,
                
            )
        } catch (t: Throwable) {
            logger.e("Error from library", t) { t.message.toString() }
        }
        return result!!.freeze()
    }

    override fun withTransport(transport: Transport): SsiAgentBuilder {
        this.transport = transport
        return this
    }

    override fun withLedgerConnector(ledgerConnector: LedgerConnector): SsiAgentBuilder {
        this.ledgerConnector = ledgerConnector
        return this
    }

    override fun withConnectionInitiatorController(connectionInitiatorController: ConnectionInitiatorController): SsiAgentBuilder {
        this.connectionInitiatorController = connectionInitiatorController
        return this
    }

    override fun withConnectionResponderController(connectionResponderController: ConnectionResponderController): SsiAgentBuilder {
        this.connectionResponderController = connectionResponderController
        return this
    }

    override fun withCredReceiverController(credReceiverController: CredReceiverController): SsiAgentBuilder {
        this.credReceiverController = credReceiverController
        return this
    }

    override fun withCredIssuerController(credIssuerController: CredIssuerController): SsiAgentBuilder {
        this.credIssuerController = credIssuerController
        return this
    }

    override fun withCredPresenterController(credPresenterController: CredPresenterController): SsiAgentBuilder {
        this.credPresenterController = credPresenterController
        return this
    }

    override fun withCredVerifierController(credVerifierController: CredVerifierController): SsiAgentBuilder {
        this.credVerifierController = credVerifierController
        return this
    }

    override fun withStatefulConnectionController(statefulConnectionController: StatefulConnectionController): SsiAgentBuilder {
        this.statefulConnectionController = statefulConnectionController
        return this
    }

    override fun withTrustPingController(trustPingController: TrustPingController): SsiAgentBuilder {
        this.trustPingController = trustPingController
        return this
    }

}
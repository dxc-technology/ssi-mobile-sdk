package com.dxc.ssi.agent.api.impl

import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.SsiAgentApi
import com.dxc.ssi.agent.api.SsiAgentBuilder
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionResponderController
import com.dxc.ssi.agent.api.callbacks.issue.CredIssuerController
import com.dxc.ssi.agent.api.callbacks.issue.CredReceiverController
import com.dxc.ssi.agent.api.callbacks.verification.CredPresenterController
import com.dxc.ssi.agent.api.callbacks.verification.CredVerifierController
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.*
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.transport.WebSocketTransportImpl
import com.dxc.ssi.agent.wallet.indy.*

class SsiAgentBuilderImpl : SsiAgentBuilder {

    private var transport: Transport? = null
    private var issuer: Issuer? = null
    private var prover: Prover? = null
    private var verifier: Verifier? = null
    private var trustee: Trustee? = null
    private var walletHolder: WalletHolder? = null
    private var ledgerConnector: LedgerConnector? = null
    private var connectionInitiatorController: ConnectionInitiatorController? = null
    private var connectionResponderController: ConnectionResponderController? = null
    private var credReceiverController: CredReceiverController? = null
    private var credIssuerController: CredIssuerController? = null
    private var credPresenterController: CredPresenterController? = null
    private var credVerifierController: CredVerifierController? = null

    override fun build(): SsiAgentApi {

        if (transport == null) {
            transport = WebSocketTransportImpl()
        }
        if (ledgerConnector == null)
            ledgerConnector = IndyLedgerConnector()

        if (issuer == null)
            issuer = IndyIssuer()
        if (verifier == null)
            verifier = IndyVerifier()
        if (trustee == null)
            trustee = IndyTrustee()
        if (prover == null)
            prover = IndyProver()
        if (walletHolder == null)
            walletHolder = IndyWalletHolder()


        val walletConnector = WalletConnector(
            issuer = issuer,
            prover = prover,
            verifier = verifier,
            trustee = trustee,
            walletHolder = walletHolder!!
        )

        //TODO: think about some sensible defaults for those callbacks
        val callbacks = Callbacks(
            connectionInitiatorController,
            connectionResponderController,
            credReceiverController,
            credIssuerController,
            credPresenterController,
            credVerifierController
        )



        return SsiAgentApiImpl(
            transport = transport!!,
            walletConnector = walletConnector,
            ledgerConnector = ledgerConnector!!,
            callbacks = callbacks
        )

    }

    override fun withTransport(transport: Transport): SsiAgentBuilder {
        this.transport = transport
        return this
    }

    override fun withProver(prover: Prover): SsiAgentBuilder {
        this.prover = prover
        return this
    }

    override fun withIssuer(issuer: Issuer): SsiAgentBuilder {
        this.issuer = issuer
        return this
    }

    override fun withVerifier(verifier: Verifier): SsiAgentBuilder {
        this.verifier = verifier
        return this
    }

    override fun withTrustee(trustee: Trustee): SsiAgentBuilder {
        this.trustee = trustee
        return this
    }

    override fun withWalletHolder(trustee: WalletHolder): SsiAgentBuilder {
        this.walletHolder = walletHolder
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

}
package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import kotlinx.coroutines.runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

class LibIndyAccessTest {

    @Test
   // @Ignore
    fun simpleLibIndyCall() {

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
            ipAddress = "192.168.0.117")

        val ledgerConnector =
            IndyLedgerConnector(indyLedgerConnectorConfiguration)
        val walletHolder = IndyWalletHolder()
        val issuer = IndyIssuer(walletHolder!!)
        val verifier = IndyVerifier(walletHolder!!)
        val trustee = IndyTrustee(walletHolder!!)
        val prover = IndyProver(walletHolder!!)

        val walletConnector = WalletConnector(
            issuer = issuer,
            prover = prover,
            verifier = verifier,
            trustee = trustee,
            walletHolder = walletHolder!!
        )

        runBlocking {

            walletConnector.walletHolder.openOrCreateWallet()
            ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did


            walletConnector.prover!!.createMasterSecret(Configuration.masterSecretId)
        }



    }
}
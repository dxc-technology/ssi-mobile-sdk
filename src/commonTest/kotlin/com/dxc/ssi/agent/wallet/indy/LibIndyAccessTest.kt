package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnector
import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import kotlin.test.Ignore
import kotlin.test.Test

class LibIndyAccessTest {

    @Test
    @Ignore
    fun simpleLibIndyCall() {


        val ledgerConnector =
            IndyLedgerConnector(IndyLedgerConnectorConfiguration("/home/ifedyanin/source/github/fedyiv/ssi-mobile-sdk-lumedic/files/docker_pool_transactions_genesis.txt"))
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


        walletConnector.walletHolder.openOrCreateWallet()
        ledgerConnector.did = walletConnector.walletHolder.getIdentityDetails().did


        walletConnector.prover!!.createMasterSecret(Configuration.masterSecretId)


    }
}
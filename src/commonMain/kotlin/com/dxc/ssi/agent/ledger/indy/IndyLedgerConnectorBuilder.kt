package com.dxc.ssi.agent.ledger.indy

import co.touchlab.stately.freeze

class IndyLedgerConnectorBuilder {

    private var genesisFilePath: String? = null
    private var ipAddress: String? = null
    private var genesisMode: GenesisMode? =  null
    private var generatedGenesysFileName: String? = null
    private var retryTimes: Int? = null
    private var retryDelayMs: Long? = null

    fun build(): IndyLedgerConnector {

        //TODO: add checks fir mutually exclusive params here
        if(genesisFilePath == null) {
            genesisFilePath = "./docker_pool_transactions_genesis.txt"
        }

        if(ipAddress == null) {
            ipAddress = "127.0.0.1"
        }

        if(genesisMode == null) {
            genesisMode = GenesisMode.SOVRIN_BUILDERNET
        }

        if(generatedGenesysFileName == null) {
            generatedGenesysFileName = "genesys.txn"
        }

        if(retryTimes == null) {
            retryTimes = 5
        }

        if (retryDelayMs == null) {
            retryDelayMs = 5000
        }

        val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisFilePath = genesisFilePath!!,
            ipAddress = ipAddress!!,
            genesisMode = genesisMode!!,
            generatedGenesysFileName = generatedGenesysFileName!!,
            retryTimes = retryTimes!!,
            retryDelayMs = retryDelayMs!!
        )

        return IndyLedgerConnector(indyLedgerConnectorConfiguration).freeze()
    }

    fun withGenesisFilePath(genesisFilePath: String) : IndyLedgerConnectorBuilder {
        this.genesisFilePath = genesisFilePath
        return this
    }
    fun withIpAddress(ipAddress: String) : IndyLedgerConnectorBuilder {
        this.ipAddress = ipAddress
        return this
    }
    fun withGenesisMode(genesisMode: GenesisMode) : IndyLedgerConnectorBuilder {
        this.genesisMode = genesisMode
        return this
    }
    fun withGeneratedGenesysFileName(generatedGenesysFileName: String) : IndyLedgerConnectorBuilder {
        this.generatedGenesysFileName = generatedGenesysFileName
        return this
    }

    fun withRetryTimes(retryTimes: Int) : IndyLedgerConnectorBuilder {
        this.retryTimes = retryTimes
        return this
    }

    fun withRetryDelayMs(retryDelayMs: Long) : IndyLedgerConnectorBuilder {
        this.retryDelayMs = retryDelayMs
        return this
    }

}
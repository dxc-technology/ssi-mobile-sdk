package com.dxc.ssi.agent.ledger.indy

data class IndyLedgerConnectorConfiguration(
    val genesisFilePath: String = "./docker_pool_transactions_genesis.txt",
    //Consider ktor ip address here, or some other ip related class
    val ipAddress: String = "127.0.0.1",
    val genesisMode: GenesisMode = GenesisMode.FILE,
    val generatedGenesysFileName: String = "genesys.txn",
    val retryTimes: Int = 5,
    val retryDelayMs: Long = 5000
) {
    enum class GenesisMode {
        IP,
        FILE
    }
}

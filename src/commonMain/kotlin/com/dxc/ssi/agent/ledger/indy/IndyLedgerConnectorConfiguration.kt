package com.dxc.ssi.agent.ledger.indy

data class IndyLedgerConnectorConfiguration(
    //TODO: see where those configs should be located
    //TODO: change default config to some sensitive value
    val genesisFilePath: String = "./docker_pool_transactions_genesis.txt",
    //Consider ktor ip address here, or some other ip related class
    val ipAddress: String = "127.0.0.1",
    val genesisMode: GenesisMode = GenesisMode.FILE
) {
    enum class GenesisMode {
        IP,
        FILE
    }
}

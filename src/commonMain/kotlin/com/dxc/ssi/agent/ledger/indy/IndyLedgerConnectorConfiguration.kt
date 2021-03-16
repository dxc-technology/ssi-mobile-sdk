package com.dxc.ssi.agent.ledger.indy

data class IndyLedgerConnectorConfiguration(
    //TODO: see where those configs should be located
    //TODO: change default config to some sensitive value
    val genesisFilePath: String = "/home/ifedyanin/source/github/fedyiv/ssi-mobile-sdk-lumedic/files/docker_pool_transactions_genesis.txt"
)

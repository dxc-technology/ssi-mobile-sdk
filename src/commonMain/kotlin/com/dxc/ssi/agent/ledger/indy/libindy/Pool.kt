package com.dxc.ssi.agent.ledger.indy.libindy

expect class Pool(poolHandle: Int) {
    //TODO: replace it with property getter/setter
    fun getPoolHandle(): Int

    companion object {
        suspend fun setProtocolVersion(protocolVersion: Long)
        suspend fun createPoolLedgerConfig(configName: String, config: String)
        suspend fun openPoolLedger(configName: String, config: String): Pool
    }
}
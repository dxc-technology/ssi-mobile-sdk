package com.dxc.ssi.agent.ledger.indy.libindy

actual class Pool actual constructor(poolHandle: Int) {
    actual fun getPoolHandle(): Int {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual suspend fun setProtocolVersion(protocolVersion: Long) {
            TODO("Not yet implemented")
        }

        actual suspend fun createPoolLedgerConfig(configName: String, config: String) {
            TODO("Not yet implemented")
        }

        actual suspend fun openPoolLedger(
            configName: String,
            config: String
        ): Pool {
            TODO("Not yet implemented")
        }
    }
}
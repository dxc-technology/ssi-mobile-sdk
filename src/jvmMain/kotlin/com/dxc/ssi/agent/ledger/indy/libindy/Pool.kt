package com.dxc.ssi.agent.ledger.indy.libindy

actual class Pool actual constructor(private val poolHandle: Int) {

    constructor(poolHandle: Int, pool: org.hyperledger.indy.sdk.pool.Pool) : this(poolHandle) {
        this.pool = pool
    }

    //Storing original pool as it is taken by java wrapper
    lateinit var pool: org.hyperledger.indy.sdk.pool.Pool

    actual fun getPoolHandle(): Int {
        return poolHandle
    }

    actual companion object {
        actual suspend fun setProtocolVersion(protocolVersion: Long) {
            org.hyperledger.indy.sdk.pool.Pool.setProtocolVersion(protocolVersion.toInt()).get()
        }

        actual suspend fun createPoolLedgerConfig(configName: String, config: String) {
            org.hyperledger.indy.sdk.pool.Pool.createPoolLedgerConfig(configName, config).get()
        }

        actual suspend fun openPoolLedger(
            configName: String,
            config: String
        ): Pool {
            val result = org.hyperledger.indy.sdk.pool.Pool.openPoolLedger(configName, config).get()
            return Pool(result.poolHandle, result)
        }
    }
}
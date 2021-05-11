package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.genesis.GenesisGenerator
import com.dxc.ssi.agent.ledger.indy.libindy.Pool
import com.dxc.ssi.agent.ledger.indy.libindy.PoolJSONParameters
import com.dxc.ssi.agent.wallet.indy.utils.EnvironmentUtils
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.serialization.json.Json
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import kotlinx.serialization.encodeToString

actual object PoolHelper {

    const val DEFAULT_POOL_NAME = "TestPool"


    /**
     * Checks if pool ledger with [poolName] exists
     */
    fun exists(poolName: String): Boolean {
        val poolDir = EnvironmentUtils.getIndyPoolPath(poolName)

        return NSFileManager().fileExistsAtPath(poolDir)
    }

    /**
     * Creates pool ledger with [genesisFile] and [poolName]
     *
     * @param genesisFile: [File] - file with genesis transaction
     * @param poolName: [String] - name of the pool
     *
     * @throws ExecutionException with cause [PoolLedgerConfigExistsException]
     */

    suspend fun createNonExisting(genesisFile: String, poolName: String = DEFAULT_POOL_NAME) {
        val ledgerConfig = Json.encodeToString(PoolJSONParameters.CreatePoolLedgerConfigJSONParameter(genesisFile))

        Pool.createPoolLedgerConfig(poolName, ledgerConfig)
    }

    /**
     * Creates new or recreates existing pool ledger files with [genesisFile] and [poolName].
     */

    suspend fun createOrTrunc(genesisFile: String, poolName: String = DEFAULT_POOL_NAME) {
        if (exists(poolName))
        {
            val error: CPointer<ObjCObjectVar<NSError?>>? = null
            NSFileManager().removeItemAtPath(EnvironmentUtils.getIndyPoolPath(poolName), error)
        }

        createNonExisting(genesisFile, poolName)
    }

    /**
     * Opens existing pool ledger with [poolName] and checks connection using [poolConfig]
     */

    suspend fun openExisting(
        poolName: String = DEFAULT_POOL_NAME,
        poolConfig: PoolJSONParameters.OpenPoolLedgerJSONParameter = PoolJSONParameters.OpenPoolLedgerJSONParameter(
            null,
            null
        )
    ): Pool {
        if (!exists(poolName))
            throw RuntimeException("Pool files ${EnvironmentUtils.getIndyPoolPath(poolName)} don't exist")

        return Pool.openPoolLedger(poolName, Json.encodeToString(poolConfig))
    }

    /**
     * Opens existing pool ledger with [poolName] and checks connection using [poolConfig] or creates new pool ledger
     * with [genesisFile] and [poolName]
     */

    suspend fun openOrCreate(
        genesisFile: String,
        poolName: String = DEFAULT_POOL_NAME,
        poolConfig: PoolJSONParameters.OpenPoolLedgerJSONParameter = PoolJSONParameters.OpenPoolLedgerJSONParameter(
            null,
            null
        )
    ): Pool {
        if (!exists(poolName))
            createNonExisting(genesisFile, poolName)

        return openExisting(poolName, poolConfig)
    }

    actual suspend fun openOrCreateFromFilename(filename: String): Pool {
        Pool.setProtocolVersion(2)
        return openOrCreate(filename)
    }

    actual suspend fun openOrCreateFromIp(
        ipAddress: String,
        dir: String
    ): Pool {


        val genesysGenerator = GenesisGenerator(ipAddress,dir)
        val filename = genesysGenerator.initGenesisFile()

        return openOrCreateFromFilename(filename)

    }
}
package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.IndyLedgerConnectorConfiguration
import com.dxc.ssi.agent.ledger.indy.genesis.GenesisGenerator
import com.dxc.ssi.agent.ledger.indy.libindy.Pool
import com.dxc.ssi.agent.ledger.indy.libindy.PoolJSONParameters
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.FileUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object PoolHelper {
    const val DEFAULT_POOL_NAME = "TestPool"

    /**
     * Checks if pool ledger with [poolName] exists
     */
    fun exists(poolName: String): Boolean {
        val poolDir = EnvironmentUtils.getIndyPoolPath(poolName)

        return FileUtils.fileExists(poolDir)
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
            FileUtils.deleteRecursively(genesisFile)

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

    suspend fun openOrCreateFromFilename(filename: String): Pool {
        //TODO: seems that JVM version does not work on Mac OS. Ensure that it works on Ubuntu. Prepare instructions to setup macos env
        Pool.setProtocolVersion(2)
        return openOrCreate(filename)
    }

    suspend fun openOrCreateCustomGenesis(
        genesisMode: IndyLedgerConnectorConfiguration.GenesisMode,
        ipAddress: String?,
        dir: String,
        generatedGenesisFileName: String = "genesis.txn"
    ): Pool {
        val genesysGenerator =
            GenesisGenerator(genesisMode, ipAddress, dir, generatedGenesisFileName)
        val filename = genesysGenerator.initGenesisFile()

        return openOrCreateFromFilename(filename)
    }
}
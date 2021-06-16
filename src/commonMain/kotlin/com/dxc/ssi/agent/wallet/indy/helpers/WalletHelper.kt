package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.exceptions.WalletDoesNotExistException
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.FileUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object WalletHelper {
    /**
     * Checks if wallet with [walletName] exists
     */
    fun exists(walletName: String): Boolean {
        val walletDir = EnvironmentUtils.getIndyWalletPath(walletName)
        return FileUtils.fileExists(walletDir)
    }

    /**
     * Creates wallet with [config] and [password]
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     */
    suspend fun createNonExisting(config: WalletConfig, password: WalletPassword) {
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        Wallet.createWallet(walletConfigJson, walletPasswordJson)
    }

    /**
     * Shortcut to [createNonExisting]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     *
     */
    suspend fun createNonExisting(walletName: String, walletPassword: String) {
        createNonExisting(WalletConfig(walletName), WalletPassword(walletPassword))
    }

    /**
     * Creates new or recreates existing wallet with [config] and [password].
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     */
    suspend fun createOrTrunc(config: WalletConfig, password: WalletPassword) {
        if (exists(config.id))
            FileUtils.deleteRecursively(EnvironmentUtils.getIndyWalletPath(config.id))

        createNonExisting(config, password)
    }

    /**
     * Shortcut to [createOrTrunc]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     */
    suspend fun createOrTrunc(walletName: String, walletPassword: String) {
        createOrTrunc(WalletConfig(walletName), WalletPassword(walletPassword))
    }

    /**
     * Opens existing wallet with [config] and [password]
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    private suspend fun openExisting(config: WalletConfig, password: WalletPassword): Wallet {
        if (!exists(config.id))
            throw WalletDoesNotExistException("Wallet ${EnvironmentUtils.getIndyWalletPath(config.id)} doesn't exist")

        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        return Wallet.openWallet(walletConfigJson, walletPasswordJson)
    }

    /**
     * Shortcut to [openExisting]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    suspend fun openExisting(walletName: String, walletPassword: String): Wallet =
        openExisting(WalletConfig(walletName), WalletPassword(walletPassword))

    /**
     * Opens existing wallet or creates new then opens it using [config] and [password]
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    suspend fun openOrCreate(config: WalletConfig, password: WalletPassword): Wallet {
        if (!exists(config.id))
            createNonExisting(config, password)

        return openExisting(config, password)
    }

    /**
     * Shortcut to [openOrCreate]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    suspend fun openOrCreate(walletName: String, walletPassword: String): Wallet =
        openOrCreate(WalletConfig(walletName), WalletPassword(walletPassword))


    suspend fun createOrFail(walletName: String, walletPassword: String) {

        createOrFail(WalletConfig(walletName), WalletPassword(walletPassword))
    }

    suspend fun createOrFail(config: WalletConfig, password: WalletPassword) {

        if (exists(config.id))
            throw RuntimeException("Wallet already exists")

        createNonExisting(config, password)
    }

}
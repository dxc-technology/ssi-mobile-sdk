package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.model.WalletConfig
import com.dxc.ssi.agent.wallet.indy.model.WalletPassword
import com.dxc.ssi.agent.wallet.indy.utils.EnvironmentUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.wallet.Wallet
import org.hyperledger.indy.sdk.wallet.WalletAlreadyOpenedException
import org.hyperledger.indy.sdk.wallet.WalletExistsException
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException

/**
 * Helps to manage wallets
 */
//TODO: review this functionality and decide if it can be moved to common layer
actual object WalletHelper {
    /**
     * Checks if wallet with [walletName] exists
     */
    fun exists(walletName: String): Boolean {
        val walletDir = EnvironmentUtils.getIndyWalletPath(walletName)

        return File(walletDir).exists()
    }

    /**
     * Creates wallet with [config] and [password]
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     *
     * @throws ExecutionException with cause [WalletExistsException]
     */
    @Throws(ExecutionException::class)
    fun createNonExisting(config: WalletConfig, password: WalletPassword) {
        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        Wallet.createWallet(walletConfigJson, walletPasswordJson).get()
    }

    /**
     * Shortcut to [createNonExisting]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     *
     * @throws ExecutionException with cause [WalletExistsException]
     */
    fun createNonExisting(walletName: String, walletPassword: String) {
        createNonExisting(WalletConfig(walletName), WalletPassword(walletPassword))
    }

    /**
     * Creates new or recreates existing wallet with [config] and [password].
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     */
    @Throws(ExecutionException::class)
    fun createOrTrunc(config: WalletConfig, password: WalletPassword) {
        if (exists(config.id))
            File(EnvironmentUtils.getIndyWalletPath(config.id)).deleteRecursively()

        createNonExisting(config, password)
    }

    /**
     * Shortcut to [createOrTrunc]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     */
    actual fun createOrTrunc(walletName: String, walletPassword: String) {
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
    @Throws(FileNotFoundException::class, ExecutionException::class)
    fun openExisting(config: WalletConfig, password: WalletPassword): Wallet {
        if (!exists(config.id))
            throw FileNotFoundException("Wallet ${EnvironmentUtils.getIndyWalletPath(config.id)} doesn't exist")

        val walletConfigJson = Json.encodeToString(config)
        val walletPasswordJson = Json.encodeToString(password)

        return Wallet.openWallet(walletConfigJson, walletPasswordJson).get()
    }

    /**
     * Shortcut to [openExisting]
     *
     * @param walletName: [String]
     * @param walletPassword: [String]
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    fun openExisting(walletName: String, walletPassword: String): Wallet
            = openExisting(WalletConfig(walletName), WalletPassword(walletPassword))

    /**
     * Opens existing wallet or creates new then opens it using [config] and [password]
     *
     * @param config: [WalletConfig] - wallet configuration
     * @param password: [WalletPassword] - wallet credentials
     *
     * @throws ExecutionException with cause [WalletAlreadyOpenedException]
     */
    @Throws(ExecutionException::class)
    fun openOrCreate(config: WalletConfig, password: WalletPassword): Wallet {
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
    actual fun openOrCreate(walletName: String, walletPassword: String): Wallet
            = openOrCreate(WalletConfig(walletName), WalletPassword(walletPassword))
}


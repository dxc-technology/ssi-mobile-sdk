package com.dxc.ssi.agent.wallet.indy

import com.indylib.indy_error_t
import com.indylib.indy_handle_t
import kotlinx.cinterop.staticCFunction

/**
 * wallet.rs API
 */
/**
 * High level wrapper for wallet SDK functions.
 */
typealias MyCallbackWallet = kotlinx.cinterop.CPointer<kotlinx.cinterop.CFunction<(com.indylib.indy_handle_t /* = kotlin.Int */, com.indylib.indy_error_t /* = kotlin.UInt */) -> kotlin.Unit>>?
typealias MyCallbackWalletOpen = kotlinx.cinterop.CPointer<kotlinx.cinterop.CFunction<(com.indylib.indy_handle_t /* = kotlin.Int */, com.indylib.indy_error_t /* = kotlin.UInt */, com.indylib.indy_handle_t /* = kotlin.Int */) -> kotlin.Unit>>?


open class Wallet private constructor(val walletHandle: Int) {
    companion object {
        fun createWallet(
            config: String?,
            credentials: String?
        ): indy_error_t {
            val config2 = "{\"id\":\"wallet_1\", \"storage_type\":\"unknown_type\"}"
            //val future: CompletableFuture<Void> = CompletableFuture<Void>()
            val commandHandle: Int = 1 //= addFuture(future)
            val myExit_cb: MyCallbackWallet = staticCFunction(fun(
                xcommand_handle: indy_handle_t,
                err: indy_error_t,
            ) {
                println("wallet")
                println(xcommand_handle)
                println(err)
                return
            })
            val result: indy_error_t = com.indylib.indy_create_wallet(
                commandHandle,
                config2,
                credentials,
                myExit_cb
            )
            println(result)
            //checkResult(future, result)
            return result
        }

        fun openWallet(
            config: String?,
            credentials: String?
        ): indy_error_t {
            //val future: CompletableFuture<Wallet> = CompletableFuture<Wallet>()
            val commandHandle: Int = 2 //addFuture(future)
            val myExit_cb: MyCallbackWalletOpen = staticCFunction(fun(
                xcommand_handle1: indy_handle_t,
                err: indy_error_t,
                xcommand_handle2: indy_handle_t,
            ) {
                println(xcommand_handle1)
                println(xcommand_handle2)
                println(err)
                return
            })
            val result: indy_error_t = com.indylib.indy_open_wallet(
                commandHandle,
                config,
                credentials,
                myExit_cb
            )
            println(result)
            return result
        }
    }
}


//    /**
//     * Gets the handle for the wallet.
//     *
//     * @return The handle for the wallet.
//     */
//    /*
//	 * INSTANCE METHODS
//	 */
//    /**
//     * Closes the wallet and frees allocated resources.
//     *
//     * @return A future that resolves no value.
//     * @throws IndyException Thrown if a call to the underlying SDK fails.
//     */
//    @Throws(IndyException::class)
//    fun closeWallet(): CompletableFuture<Void> {
//        return closeWallet(this)
//    }
//
//    @Override
//    @Throws(PosixException.InterruptedException::class, ExecutionException::class, IndyException::class)
//    fun close() {
//        closeWallet().get()
//    }
//
//    companion object {
//        /*
//	 * STATIC CALLBACKS
//	 */
//        /**
//         * Callback used when function returning void completes.
//         */
//        private val voidCb: Callback = object : Callback() {
//            @SuppressWarnings(["unused", "unchecked"])
//            fun callback(xcommand_handle: Int, err: Int) {
//                val future: CompletableFuture<Void> = removeFuture(xcommand_handle) as CompletableFuture<Void>
//                if (!checkResult(future, err)) return
//                val result: Void? = null
//                future.complete(result)
//            }
//        }
//
//        /**
//         * Callback used when function returning string completes.
//         */
//        private val stringCb: Callback = object : Callback() {
//            @SuppressWarnings(["unused", "unchecked"])
//            fun callback(xcommand_handle: Int, err: Int, str: String) {
//                val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//                if (!checkResult(future, err)) return
//                future.complete(str)
//            }
//        }
//
//        /**
//         * Callback used when openWallet completes.
//         */
//        private val openWalletCb: Callback = object : Callback() {
//            @SuppressWarnings(["unused", "unchecked"])
//            fun callback(xcommand_handle: Int, err: Int, handle: Int) {
//                val future: CompletableFuture<Wallet> = removeFuture(xcommand_handle) as CompletableFuture<Wallet>
//                if (!checkResult(future, err)) return
//                val wallet = Wallet(handle)
//                future.complete(wallet)
//            }
//        }
//        /*
//	 * STATIC METHODS
//	 */
//

//
//        /**
//         * Opens the wallet with specific name.
//         *
//         * @param config Wallet configuration json.
//         * {
//         * "id": string, Identifier of the wallet.
//         * Configured storage uses this identifier to lookup exact wallet data placement.
//         * "storage_type": optional["string"], Type of the wallet storage. Defaults to 'default'.
//         * 'Default' storage type allows to store wallet data in the local file.
//         * Custom storage types can be registered with indy_register_wallet_storage call.
//         * "storage_config": optional[{config json}], Storage configuration json. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type configuration is:
//         * {
//         * "path": optional["string"], Path to the directory with wallet files.
//         * Defaults to $HOME/.indy_client/wallet.
//         * Wallet will be stored in the file {path}/{id}/sqlite.db
//         * }
//         * }
//         * @param credentials Wallet credentials json
//         * {
//         * "key": string, Key or passphrase used for wallet key derivation.
//         * Look to key_derivation_method param for information about supported key derivation methods.
//         * "rekey": optional["string"], If present than wallet master key will be rotated to a new one.
//         * "storage_credentials": optional[{credentiails object}] Credentials for wallet storage. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type should be empty.
//         * "key_derivation_method": optional[string] Algorithm to use for wallet key derivation:
//         * ARGON2I_MOD - derive secured wallet master key (used by default)
//         * ARGON2I_INT - derive secured wallet master key (less secured but faster)
//         * RAW - raw wallet key master provided (skip derivation).
//         * RAW keys can be generated with generateWalletKey call
//         * "rekey_derivation_method": optional[string] Algorithm to use for wallet rekey derivation:
//         * ARGON2I_MOD - derive secured wallet master rekey (used by default)
//         * ARGON2I_INT - derive secured wallet master rekey (less secured but faster)
//         * RAW - raw wallet master rekey provided (skip derivation).
//         * RAW keys can be generated with generateWalletKey call
//         *
//         * }
//         * @return A future that resolves no value.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)


//
//        /**
//         * Closes the specified open wallet and frees allocated resources.
//         *
//         * @param wallet The wallet to close.
//         * @return A future that resolves no value.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)
//        private fun closeWallet(
//            wallet: Wallet
//        ): CompletableFuture<Void> {
//            ParamGuard.notNull(wallet, "wallet")
//            val future: CompletableFuture<Void> = CompletableFuture<Void>()
//            val commandHandle: Int = addFuture(future)
//            val handle = wallet.walletHandle
//            val result: Int = LibIndy.api.indy_close_wallet(
//                commandHandle,
//                handle,
//                voidCb
//            )
//            checkResult(future, result)
//            return future
//        }
//
//        /**
//         * Deletes an existing wallet.
//         *
//         * @param config Wallet configuration json.
//         * {
//         * "id": string, Identifier of the wallet.
//         * Configured storage uses this identifier to lookup exact wallet data placement.
//         * "storage_type": optional["string"], Type of the wallet storage. Defaults to 'default'.
//         * 'Default' storage type allows to store wallet data in the local file.
//         * Custom storage types can be registered with indy_register_wallet_storage call.
//         * "storage_config": optional[{config json}], Storage configuration json. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type configuration is:
//         * {
//         * "path": optional["string"], Path to the directory with wallet files.
//         * Defaults to $HOME/.indy_client/wallet.
//         * Wallet will be stored in the file {path}/{id}/sqlite.db
//         * }
//         * }
//         * @param credentials Wallet credentials json
//         * {
//         * "key": string, Key or passphrase used for wallet key derivation.
//         * Look to key_derivation_method param for information about supported key derivation methods.
//         * "storage_credentials": optional[{credentials json}] Credentials for wallet storage. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type should be empty.
//         * "key_derivation_method": optional[string] Algorithm to use for wallet key derivation:
//         * ARGON2I_MOD - derive secured wallet master key (used by default)
//         * ARGON2I_INT - derive secured wallet master key (less secured but faster)
//         * RAW - raw wallet key master provided (skip derivation).
//         * RAW keys can be generated with generateWalletKey call
//         * }
//         *
//         * @return A future that resolves no value.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)
//        fun deleteWallet(
//            config: String?,
//            credentials: String?
//        ): CompletableFuture<Void> {
//            val future: CompletableFuture<Void> = CompletableFuture<Void>()
//            val commandHandle: Int = addFuture(future)
//            val result: Int = LibIndy.api.indy_delete_wallet(
//                commandHandle,
//                config,
//                credentials,
//                voidCb
//            )
//            checkResult(future, result)
//            return future
//        }
//
//        /**
//         * Exports opened wallet to the file.
//         *
//         * @param wallet The wallet to export.
//         * @param exportConfigJson: JSON containing settings for input operation.
//         * {
//         * "path": "string", Path of the file that contains exported wallet content
//         * "key": string, Key or passphrase used for wallet export key derivation.
//         * Look to key_derivation_method param for information about supported key derivation methods.
//         * "key_derivation_method": optional[string] algorithm to use for export key derivation:
//         * ARGON2I_MOD - derive secured wallet export key (used by default)
//         * ARGON2I_INT - derive secured wallet export key (less secured but faster)
//         * RAW - raw wallet export master provided (skip derivation).
//         * RAW keys can be generated with generateWalletKey call
//         * }
//         * @return A future that resolves no value.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)
//        fun exportWallet(
//            wallet: Wallet,
//            exportConfigJson: String?
//        ): CompletableFuture<Void> {
//            ParamGuard.notNull(wallet, "wallet")
//            ParamGuard.notNull(exportConfigJson, "exportConfigJson")
//            val future: CompletableFuture<Void> = CompletableFuture<Void>()
//            val commandHandle: Int = addFuture(future)
//            val handle = wallet.walletHandle
//            val result: Int = LibIndy.api.indy_export_wallet(
//                commandHandle,
//                handle,
//                exportConfigJson,
//                voidCb
//            )
//            checkResult(future, result)
//            return future
//        }
//
//        /**
//         * Creates a new secure wallet with the given unique name and then imports its content
//         * according to fields provided in import_config
//         * This can be seen as an indy_create_wallet call with additional content import
//         *
//         * @param config Wallet configuration json. List of supported keys are defined by wallet type.
//         * @param credentials Wallet configuration json.
//         * {
//         * "id": string, Identifier of the wallet.
//         * Configured storage uses this identifier to lookup exact wallet data placement.
//         * "storage_type": optional["string"], Type of the wallet storage. Defaults to 'default'.
//         * 'Default' storage type allows to store wallet data in the local file.
//         * Custom storage types can be registered with indy_register_wallet_storage call.
//         * "storage_config": optional[{config json}], Storage configuration json. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type configuration is:
//         * {
//         * "path": optional["string"], Path to the directory with wallet files.
//         * Defaults to $HOME/.indy_client/wallet.
//         * Wallet will be stored in the file {path}/{id}/sqlite.db
//         * }
//         * }
//         * @param credentials Wallet credentials json
//         * {
//         * "key": string, Key or passphrase used for wallet key derivation.
//         * Look to key_derivation_method param for information about supported key derivation methods.
//         * "storage_credentials": optional[{credentials json}] Credentials for wallet storage. Storage type defines set of supported keys.
//         * Can be optional if storage supports default configuration.
//         * For 'default' storage type should be empty.
//         * "key_derivation_method": optional[string] Algorithm to use for wallet key derivation:
//         * ARGON2I_MOD - derive secured wallet master key (used by default)
//         * ARGON2I_INT - derive secured wallet master key (less secured but faster)
//         * RAW - raw wallet key master provided (skip derivation).
//         * RAW keys can be generated with generateWalletKey call
//         * }
//         * @param importConfigJson Import settings json.
//         * {
//         * "path": "string", Path of the file that contains exported wallet content
//         * "key": "string",  Key used for export of the wallet
//         * }
//         * @return A future that resolves no value.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)
//        fun importWallet(
//            config: String?,
//            credentials: String?,
//            importConfigJson: String?
//        ): CompletableFuture<Void> {
//            ParamGuard.notNull(importConfigJson, "importConfigJson")
//            val future: CompletableFuture<Void> = CompletableFuture<Void>()
//            val commandHandle: Int = addFuture(future)
//            val result: Int = LibIndy.api.indy_import_wallet(
//                commandHandle,
//                config,
//                credentials,
//                importConfigJson,
//                voidCb
//            )
//            checkResult(future, result)
//            return future
//        }
//
//        /**
//         * Generate wallet master key.
//         * Returned key is compatible with "RAW" key derivation method.
//         * It allows to avoid expensive key derivation for use cases when wallet keys can be stored in a secure enclave.
//         *
//         * @param config (optional) key configuration json.
//         * {
//         * "seed": string, (optional) Seed that allows deterministic key creation (if not set random one will be created).
//         * Can be UTF-8, base64 or hex string.
//         * }
//         *
//         * @return A future that resolves to key.
//         * @throws IndyException Thrown if a call to the underlying SDK fails.
//         */
//        @Throws(IndyException::class)
//        fun generateWalletKey(
//            config: String?
//        ): CompletableFuture<String> {
//            val future: CompletableFuture<String> = CompletableFuture<String>()
//            val commandHandle: Int = addFuture(future)
//            val result: Int = LibIndy.api.indy_generate_wallet_key(
//                commandHandle,
//                config,
//                stringCb
//            )
//            checkResult(future, result)
//            return future
//        }
//    }
//}
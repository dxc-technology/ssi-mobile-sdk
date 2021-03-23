package com.dxc.ssi.agent.wallet.indy

import com.indylib.indy_create_and_store_my_did
import com.indylib.indy_error_t
import com.indylib.indy_handle_t
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import platform.posix.sleep

typealias MyCallback = CPointer<CFunction<(indy_handle_t, indy_error_t, CPointer<ByteVar>?, CPointer<ByteVar>?) -> Unit>>

object Did {
    fun createAndStoreMyDid(
        wallet: Int, //Wallet,
        didJson: String?
    ): indy_error_t {
        val commandHandle: Int = 1 //= addFuture(future)
        val walletHandle: Int = wallet //wallet.getWalletHandle()

        val myExit_cb: MyCallback = staticCFunction(fun(
            xcommand_handle: indy_handle_t,
            err: indy_error_t,
            did: CPointer<ByteVar>?,
            verkey: CPointer<ByteVar>?
        ) {
            println("did")
            println(xcommand_handle)
            println(err)
            //println(did?.toKString())
            //println(verkey?.toKString())
            return
        })

        val result: indy_error_t = indy_create_and_store_my_did(
            commandHandle,
            walletHandle,
            didJson,
            myExit_cb
        )
        sleep(100)
        return result
    }
//
//    /**
//     * Callback used when replaceKeysStart completes.
//     */
//    private val replaceKeysStartCb: Callback = object : Callback {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, verkey: String) {
//            val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//            if (!checkResult(future, err)) return
//            future.complete(verkey)
//        }
//    }
//
//    /**
//     * Callback used when replaceKeysApply completes.
//     */
//    private val replaceKeysApplyCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int) {
//            val future: CompletableFuture<Void> = removeFuture(xcommand_handle) as CompletableFuture<Void>
//            if (!checkResult(future, err)) return
//            val result: Void? = null
//            future.complete(result)
//        }
//    }
//
//    /**
//     * Callback used when storeTheirDid completes.
//     */
//    private val storeTheirDidCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int) {
//            val future: CompletableFuture<Void> = removeFuture(xcommand_handle) as CompletableFuture<Void>
//            if (!checkResult(future, err)) return
//            val result: Void? = null
//            future.complete(result)
//        }
//    }
//
//    /**
//     * Callback used when keyForDid completes.
//     */
//    private val keyForDidCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, key: String) {
//            val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//            if (!checkResult(future, err)) return
//            future.complete(key)
//        }
//    }
//
//    /**
//     * Callback used when keyForLocalDid completes.
//     */
//    private val keyForLocalDidCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, key: String) {
//            val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//            if (!checkResult(future, err)) return
//            future.complete(key)
//        }
//    }
//
//    /**
//     * Callback used when setEndpointForDid completes.
//     */
//    private val setEndpointForDidCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int) {
//            val future: CompletableFuture<Void> = removeFuture(xcommand_handle) as CompletableFuture<Void>
//            if (!checkResult(future, err)) return
//            val result: Void? = null
//            future.complete(result)
//        }
//    }
//
//    /**
//     * Callback used when getEndpointForDid completes.
//     */
//    private val getEndpointForDidCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, endpoint: String?, transport_vk: String?) {
//            val future: CompletableFuture<EndpointForDidResult> =
//                removeFuture(xcommand_handle) as CompletableFuture<EndpointForDidResult>
//            if (!checkResult(future, err)) return
//            val result = EndpointForDidResult(endpoint, transport_vk)
//            future.complete(result)
//        }
//    }
//
//    /**
//     * Callback used when setDidMetadata completes.
//     */
//    private val setDidMetadataCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int) {
//            val future: CompletableFuture<Void> = removeFuture(xcommand_handle) as CompletableFuture<Void>
//            if (!checkResult(future, err)) return
//            val result: Void? = null
//            future.complete(result)
//        }
//    }
//
//    /**
//     * Callback used when getDidMetadata completes.
//     */
//    private val getDidMetadataCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, metadata: String) {
//            val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//            if (!checkResult(future, err)) return
//            future.complete(metadata)
//        }
//    }
//
//    /**
//     * Callback used when getAttrVerkey completes.
//     */
//    private val getAttrVerkeyCb: Callback = object : Callback() {
//        //@SuppressWarnings(["unused", "unchecked"])
//        fun callback(xcommand_handle: Int, err: Int, verkey: String) {
//            val future: CompletableFuture<String> = removeFuture(xcommand_handle) as CompletableFuture<String>
//            if (!checkResult(future, err)) return
//            future.complete(verkey)
//        }
//    }
//
//    /**
//     * Generated new signing and encryption keys for an existing DID owned by the caller.
//     *
//     * @param wallet       The wallet.
//     * @param did          The DID
//     * @param identityJson identity information as json.
//     * @return A future that resolves to a temporary verkey.
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun replaceKeysStart(
//        wallet: Wallet,
//        did: String?,
//        identityJson: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        ParamGuard.notNullOrWhiteSpace(identityJson, "identityJson")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_replace_keys_start(
//            commandHandle,
//            walletHandle,
//            did,
//            identityJson,
//            replaceKeysStartCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Apply temporary keys as main for an existing DID (owned by the caller of the library).
//     *
//     * @param wallet The wallet.
//     * @param did    The DID
//     * @return A future that resolves no value.
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun replaceKeysApply(
//        wallet: Wallet,
//        did: String?
//    ): CompletableFuture<Void> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<Void> = CompletableFuture<Void>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_replace_keys_apply(
//            commandHandle,
//            walletHandle,
//            did,
//            replaceKeysApplyCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Saves their DID for a pairwise connection in a secured Wallet so that it can be used to verify transaction.
//     *
//     * @param wallet       The wallet.
//     * @param identityJson Identity information as json.
//     * @return A future that does not resolve any value.
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun storeTheirDid(
//        wallet: Wallet,
//        identityJson: String?
//    ): CompletableFuture<Void> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(identityJson, "identityJson")
//        val future: CompletableFuture<Void> = CompletableFuture<Void>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t =indy_store_their_did(
//            commandHandle,
//            walletHandle,
//            identityJson,
//            storeTheirDidCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Returns ver key (key id) for the given DID.
//     *
//     * "keyForDid" call follow the idea that we resolve information about their DID from
//     * the ledger with cache in the local wallet. The "openWallet" call has freshness parameter
//     * that is used for checking the freshness of cached pool value.
//     *
//     * Note if you don't want to resolve their DID info from the ledger you can use
//     * "keyForLocalDid" call instead that will look only to local wallet and skip
//     * freshness checking.
//     *
//     * Note that "createAndStoreMyDid" makes similar wallet record as "createKey".
//     * As result we can use returned ver key in all generic crypto and messaging functions.
//     *
//     * @param pool   The pool.
//     * @param wallet The wallet.
//     * @param did    The DID to resolve key.
//     * @return A future resolving to a verkey
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun keyForDid(
//        pool: Pool,
//        wallet: Wallet,
//        did: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(pool, "pool")
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val poolHandle: Int = pool.getPoolHandle()
//        val result: indy_error_t = indy_key_for_did(
//            commandHandle,
//            poolHandle,
//            walletHandle,
//            did,
//            keyForDidCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Returns ver key (key id) for the given DID.
//     *
//     * "keyForLocalDid" call looks data stored in the local wallet only and skips freshness checking.
//     *
//     * Note if you want to get fresh data from the ledger you can use "keyForDid" call
//     * instead.
//     *
//     * Note that "createAndStoreMyDid" makes similar wallet record as "createKey".
//     * As result we can use returned ver key in all generic crypto and messaging functions.
//     *
//     * @param wallet The wallet.
//     * @param did    The DID to resolve key.
//     * @return A future resolving to a verkey
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun keyForLocalDid(
//        wallet: Wallet,
//        did: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_key_for_local_did(
//            commandHandle,
//            walletHandle,
//            did,
//            keyForLocalDidCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Set/replaces endpoint information for the given DID.
//     *
//     * @param wallet       The wallet.
//     * @param did          The DID to resolve endpoint.
//     * @param address      The DIDs endpoint address.
//     * @param transportKey The DIDs transport key (ver key, key id).
//     * @return A future that resolves no value.
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun setEndpointForDid(
//        wallet: Wallet,
//        did: String?,
//        address: String?,
//        transportKey: String?
//    ): CompletableFuture<Void> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        ParamGuard.notNull(address, "address")
//        ParamGuard.notNullOrWhiteSpace(transportKey, "transportKey")
//        val future: CompletableFuture<Void> = CompletableFuture<Void>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_set_endpoint_for_did(
//            commandHandle,
//            walletHandle,
//            did,
//            address,
//            transportKey,
//            setEndpointForDidCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Returns endpoint information for the given DID.
//     *
//     * @param wallet The wallet.
//     * @param pool The pool.
//     * @param did  The DID to resolve endpoint.
//     * @return A future resolving to a object containing endpoint and transportVk
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun getEndpointForDid(
//        wallet: Wallet,
//        pool: Pool,
//        did: String?
//    ): CompletableFuture<EndpointForDidResult> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNull(pool, "pool")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<EndpointForDidResult> = CompletableFuture<EndpointForDidResult>()
//        val commandHandle: Int = addFuture(future)
//        val poolHandle: Int = pool.getPoolHandle()
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_get_endpoint_for_did(
//            commandHandle,
//            walletHandle,
//            poolHandle,
//            did,
//            getEndpointForDidCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Saves/replaces the meta information for the giving DID in the wallet.
//     *
//     * @param wallet   The wallet.
//     * @param did      The DID to store metadata.
//     * @param metadata The meta information that will be store with the DID.
//     * @return A future that resolves no value.
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun setDidMetadata(
//        wallet: Wallet,
//        did: String?,
//        metadata: String?
//    ): CompletableFuture<Void> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        ParamGuard.notNull(metadata, "metadata")
//        val future: CompletableFuture<Void> = CompletableFuture<Void>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        CPointer<CFunction<(indy_handle_t /* = Int */, indy_error_t /* = UInt */) → Unit>>
//        val result: indy_error_t = indy_set_did_metadata(
//            commandHandle,
//            walletHandle,
//            did,
//            metadata,
//            setDidMetadataCb
//        )
//        checkResult(future, result)
//        return future
//    }
//    fun ()
//
//    /**
//     * Retrieves the meta information for the giving DID in the wallet.
//     *
//     * @param wallet The wallet.
//     * @param did    The DID to retrieve metadata.
//     * @return A future resolving to a metadata
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun getDidMetadata(
//        wallet: Wallet,
//        did: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_get_did_metadata(
//            commandHandle,
//            walletHandle,
//            did,
//            getDidMetadataCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Retrieves the information about the giving DID in the wallet.
//     *
//     * @param wallet The wallet.
//     * @param did    The DID to retrieve metadata.
//     * @return A future resolving to a did data: {
//     * "did": string - DID stored in the wallet,
//     * "verkey": string - The DIDs transport key (ver key, key id),
//     * "tempVerkey": string - Future DIDs transport key (ver key, key id), after rotation of keys is done.
//     * "metadata": string - The meta information stored with the DID
//     * }
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun getDidWithMeta(
//        wallet: Wallet,
//        did: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(wallet, "wallet")
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_get_my_did_with_meta(
//            commandHandle,
//            walletHandle,
//            did,
//            getDidMetadataCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Retrieves the information about all DIDs stored in the wallet.
//     *
//     * @param wallet The wallet.
//     * @return A future resolving to a list of dids: [{
//     * "did": string - DID stored in the wallet,
//     * "verkey": string - The DIDs transport key (ver key, key id).,
//     * "metadata": string - The meta information stored with the DID
//     * }]
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun getListMyDidsWithMeta(
//        wallet: Wallet
//    ): CompletableFuture<String> {
//        ParamGuard.notNull(wallet, "wallet")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val walletHandle: Int = wallet.getWalletHandle()
//        val result: indy_error_t = indy_list_my_dids_with_meta(
//            commandHandle,
//            walletHandle,
//            getDidMetadataCb
//        )
//        checkResult(future, result)
//        return future
//    }
//
//    /**
//     * Retrieves abbreviated verkey if it is possible otherwise return full verkey.
//     *
//     * @param did   DID.
//     * @param verkey    The DIDs verification key,
//     * @return A future resolving to the DIDs verification key in either abbreviated or full form
//     * @throws IndyException Thrown if an error occurs when calling the underlying SDK.
//     */
//    @Throws(IndyException::class)
//    fun AbbreviateVerkey(
//        did: String?,
//        verkey: String?
//    ): CompletableFuture<String> {
//        ParamGuard.notNullOrWhiteSpace(did, "did")
//        ParamGuard.notNullOrWhiteSpace(did, "verkey")
//        val future: CompletableFuture<String> = CompletableFuture<String>()
//        val commandHandle: Int = addFuture(future)
//        val result: indy_error_t = indy_abbreviate_verkey(
//            commandHandle,
//            did,
//            verkey,
//            getAttrVerkeyCb
//        )
//        checkResult(future, result)
//        return future
//    }
}
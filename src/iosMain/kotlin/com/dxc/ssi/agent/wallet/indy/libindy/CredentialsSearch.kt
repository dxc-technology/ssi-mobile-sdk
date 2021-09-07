package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.*
import kotlinx.cinterop.staticCFunction

actual class CredentialsSearch actual constructor() {

    private var searchHandle: Int? = null

    data class OpenCredentialsSearchCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val searchHandle: Int,
        val totalCount: UInt
    ) : CallbackData

    actual suspend fun open(wallet: Wallet, queryJson: String?) {

        val commandHandle = callbackHandler.prepareCallback()

        val callback =
            staticCFunction { commandHandle: Int, errorCode: UInt, searchHandle: Int, totalCount: UInt
                ->
                initRuntimeIfNeeded()
                callbackHandler.setCallbackResult(OpenCredentialsSearchCallbackResult(commandHandle,errorCode,searchHandle, totalCount))
            }



        indy_prover_search_credentials(commandHandle, wallet.getWalletHandle(),queryJson, callback)

        val callbackResult = callbackHandler.waitForCallbackResult(commandHandle) as OpenCredentialsSearchCallbackResult
        searchHandle =  callbackResult.searchHandle
    }

    actual suspend fun fetchNextCredentials(count: Int): String {
        val commandHandle = callbackHandler.prepareCallback()

        indy_prover_fetch_credentials(
            commandHandle,
            searchHandle!!,
            count.toUInt(),
            StringCallback.callback)

        val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result

        return result.stringResult
    }

    actual suspend fun closeSearch() {
        val commandHandle = callbackHandler.prepareCallback()
        indy_prover_close_credentials_search(
            commandHandle,
            searchHandle!!,
            SimpleCallback.callback);
        callbackHandler.waitForCallbackResult(commandHandle)
    }

}
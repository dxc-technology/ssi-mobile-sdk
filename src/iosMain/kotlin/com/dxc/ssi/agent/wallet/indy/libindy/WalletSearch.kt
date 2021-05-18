package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.IntCallback
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.indy_close_wallet_search
import com.indylib.indy_fetch_wallet_search_next_records
import com.indylib.indy_open_wallet_search
import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString


actual class WalletSearch actual constructor() {

    var searchHandle: Int? = null

    actual suspend fun open(
        wallet: Wallet,
        type: String,
        queryJson: String,
        optionsJson: String
    ) {
        val commandHandle = callbackHandler.prepareCallback()


        indy_open_wallet_search(
            commandHandle,
            wallet.getWalletHandle(),
            type,
            queryJson,
            optionsJson,
            IntCallback.callback
        )

        val result = callbackHandler.waitForCallbackResult(commandHandle) as IntCallback.Result

        searchHandle = result.handle
    }

    actual suspend fun closeSearch() {

        val commandHandle = callbackHandler.prepareCallback()

        //TODO: deal with NPE
        indy_close_wallet_search(
            commandHandle,
            searchHandle!!,
            SimpleCallback.callback
        )

        callbackHandler.waitForCallbackResult(commandHandle)
    }

    actual suspend fun searchFetchNextRecords(
        wallet: Wallet,
        count: Int
    ): String {
        val commandHandle = callbackHandler.prepareCallback()

        indy_fetch_wallet_search_next_records(
            commandHandle,
            wallet.getWalletHandle(),
            searchHandle!!,
            count.toUInt(),
            StringCallback.callback
        )

        val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result

        return result.stringResult
    }

}
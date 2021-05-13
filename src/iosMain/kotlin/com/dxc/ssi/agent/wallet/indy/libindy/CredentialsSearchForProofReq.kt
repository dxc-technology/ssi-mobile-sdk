package com.dxc.ssi.agent.wallet.indy.libindy

import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.IntCallback
import com.dxc.ssi.agent.callback.impl.SimpleCallback
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.indy_prover_close_credentials_search_for_proof_req
import com.indylib.indy_prover_fetch_credentials_for_proof_req
import com.indylib.indy_prover_search_credentials_for_proof_req

actual class CredentialsSearchForProofReq actual constructor() {

    private var searchHandle: Int? = null

    actual suspend fun open(
        wallet: Wallet,
        proofReqJson: String,
        extraQueryJson: String?
    ) {

        val commandHandle = callbackHandler.prepareCallback()

        indy_prover_search_credentials_for_proof_req(
            commandHandle,
            wallet.getWalletHandle(),
            proofReqJson,
            extraQueryJson,
            IntCallback.callback
        )

        val result = callbackHandler.waitForCallbackResult(commandHandle) as IntCallback.Result
        searchHandle = result.handle
    }

    actual suspend fun fetchNextCredentials(itemRef: String, count: Int): String {
        val commandHandle = callbackHandler.prepareCallback()

        indy_prover_fetch_credentials_for_proof_req(
            commandHandle,
            searchHandle!!,
            itemRef,
            count.toUInt(),
            StringCallback.callback)

        val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result

        return result.stringResult

    }

    actual suspend fun closeSearch() {
        val commandHandle = callbackHandler.prepareCallback()
        indy_prover_close_credentials_search_for_proof_req(
            commandHandle,
            searchHandle!!,
            SimpleCallback.callback);
        callbackHandler.waitForCallbackResult(commandHandle)
    }


}
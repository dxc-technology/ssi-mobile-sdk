package com.dxc.ssi.agent.ledger.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.indylib.indy_build_get_cred_def_request
import com.indylib.indy_parse_get_cred_def_response
import com.indylib.indy_submit_request
import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString


actual class Ledger {

    data class ParseGetCredDefResponseCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val credDefId: String,
        val credDefJson: String
    ) : CallbackData

    actual companion object {
        actual fun buildGetSchemaRequest(submitterDid: String, id: String): String {
            TODO("Not yet implemented")
        }

        actual suspend fun buildGetCredDefRequest(submitterDid: String, id: String): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_build_get_cred_def_request(
                commandHandle,
                submitterDid,
                id,
                StringCallback.callback
            )

            println("Pool -> Before waiting for callback")

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult

        }

        actual fun buildGetRevocRegDefRequest(submitterDid: String, id: String): String {
            TODO("Not yet implemented")
        }

        actual fun buildGetRevocRegDeltaRequest(
            submitterDid: String,
            revocRegDefId: String,
            from: Long,
            to: Long
        ): String {
            TODO("Not yet implemented")
        }

        actual suspend fun submitRequest(
            pool: Pool,
            requestJson: String
        ): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_submit_request(
                commandHandle,
                pool.getPoolHandle(),
                requestJson,
                StringCallback.callback
            )

            println("Pool -> Before waiting for callback")

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult


        }

        actual fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult {
            TODO("Not yet implemented")
        }

        actual suspend fun parseGetCredDefResponse(getCredDefResponse: String): ParseResponseResult {
            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt, credDefId: CPointer<ByteVarOf<Byte>>?, credDefJson: CPointer<ByteVarOf<Byte>>?
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        ParseGetCredDefResponseCallbackResult(
                            commandHandle,
                            errorCode,
                            credDefId!!.toKString(),
                            credDefJson!!.toKString()
                        )
                    )
                }

            indy_parse_get_cred_def_response(
                commandHandle,
                getCredDefResponse,
                callback
            )

            val result = callbackHandler.waitForCallbackResult(commandHandle) as ParseGetCredDefResponseCallbackResult
            return ParseResponseResult(result.credDefJson)
        }

        actual fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult {
            TODO("Not yet implemented")
        }

        actual fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult {
            TODO("Not yet implemented")
        }
    }
}
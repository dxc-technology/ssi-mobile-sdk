package com.dxc.ssi.agent.ledger.indy.libindy

import com.dxc.ssi.agent.callback.CallbackData
import com.dxc.ssi.agent.callback.callbackHandler
import com.dxc.ssi.agent.callback.impl.StringCallback
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.indylib.*
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

    data class ParseGetSchemaResponseCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val schemaId: String,
        val schemaJson: String
    ) : CallbackData

    data class ParseGetRevocRegDefResponseCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val revocRegDefId: String,
        val revocRegDefJson: String
    ) : CallbackData

    data class ParseGetRevocRegDeltaResponseCallbackResult(
        override val commandHandle: Int,
        override val errorCode: UInt,
        val revocRegDefId: String,
        val revocRegDeltaJson: String,
        val timestamp: ULong
    ) : CallbackData


    actual companion object {
        private val logger: Kermit = Kermit(LogcatLogger())
        actual suspend fun buildGetSchemaRequest(submitterDid: String, id: String): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_build_get_schema_request(
                commandHandle,
                submitterDid,
                id,
                StringCallback.callback
            )

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult
        }

        actual suspend fun buildGetCredDefRequest(submitterDid: String, id: String): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_build_get_cred_def_request(
                commandHandle,
                submitterDid,
                id,
                StringCallback.callback
            )

            logger.log(Severity.Debug,"",null) { "Pool -> Before waiting for callback" }


            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult

        }

        actual suspend fun buildGetRevocRegDefRequest(submitterDid: String, id: String): String {

            val commandHandle = callbackHandler.prepareCallback()

            indy_build_get_revoc_reg_def_request(
                commandHandle,
                submitterDid,
                id,
                StringCallback.callback
            )

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult
        }

        actual suspend fun buildGetRevocRegDeltaRequest(
            submitterDid: String,
            revocRegDefId: String,
            from: Long,
            to: Long
        ): String {
            val commandHandle = callbackHandler.prepareCallback()
            indy_build_get_revoc_reg_delta_request(
                commandHandle,
                submitterDid,
                revocRegDefId,
                from,
                to,
                StringCallback.callback
            )
            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult
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

            logger.log(Severity.Debug,"",null) { "Pool -> Before waiting for callback" }

            val result = callbackHandler.waitForCallbackResult(commandHandle) as StringCallback.Result
            return result.stringResult


        }

        actual suspend fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult {
            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt, schemaId: CPointer<ByteVarOf<Byte>>?, schemaJson: CPointer<ByteVarOf<Byte>>?
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        ParseGetSchemaResponseCallbackResult(
                            commandHandle,
                            errorCode,
                            schemaId!!.toKString(),
                            schemaJson!!.toKString()
                        )
                    )
                }

            indy_parse_get_schema_response(
                commandHandle,
                getSchemaResponse,
                callback
            )

            val result = callbackHandler.waitForCallbackResult(commandHandle) as ParseGetSchemaResponseCallbackResult
            return ParseResponseResult(result.schemaJson)

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

        actual suspend fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult {

            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt, revocRegDefId: CPointer<ByteVarOf<Byte>>?, revocRegDefJson: CPointer<ByteVarOf<Byte>>?
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        ParseGetRevocRegDefResponseCallbackResult(
                            commandHandle,
                            errorCode,
                            revocRegDefId!!.toKString(),
                            revocRegDefJson!!.toKString()
                        )
                    )
                }

            indy_parse_get_revoc_reg_def_response(
                commandHandle,
                getRevocRegDefResponse,
                callback
            )

            val result =
                callbackHandler.waitForCallbackResult(commandHandle) as ParseGetRevocRegDefResponseCallbackResult
            return ParseResponseResult(result.revocRegDefJson)
        }

        actual suspend fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult {

            val commandHandle = callbackHandler.prepareCallback()

            val callback =
                staticCFunction { commandHandle: Int, errorCode: UInt, revocRegDefId: CPointer<ByteVarOf<Byte>>?, revocRegDeltaJson: CPointer<ByteVarOf<Byte>>?, timestamp: ULong
                    ->
                    initRuntimeIfNeeded()
                    callbackHandler.setCallbackResult(
                        ParseGetRevocRegDeltaResponseCallbackResult(
                            commandHandle,
                            errorCode,
                            revocRegDefId!!.toKString(),
                            revocRegDeltaJson!!.toKString(),
                            timestamp
                        )
                    )
                }

            indy_parse_get_revoc_reg_delta_response(
                commandHandle,
                getRevocRegDeltaResponse,
                callback
            )

            val result =
                callbackHandler.waitForCallbackResult(commandHandle) as ParseGetRevocRegDeltaResponseCallbackResult
            return ParseRegistryResponseResult(result.timestamp.toLong(), result.revocRegDeltaJson)

        }
    }
}
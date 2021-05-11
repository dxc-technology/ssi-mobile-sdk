package com.dxc.ssi.agent.ledger.indy.libindy

import org.hyperledger.indy.sdk.ledger.Ledger

actual class Ledger {
    actual companion object {
        actual suspend fun buildGetSchemaRequest(submitterDid: String, id: String): String {
            return Ledger.buildGetSchemaRequest(submitterDid, id).get()
        }

        actual suspend fun buildGetCredDefRequest(submitterDid: String, id: String): String {
            return Ledger.buildGetCredDefRequest(submitterDid, id).get()
        }

        actual suspend fun buildGetRevocRegDefRequest(submitterDid: String, id: String): String {
            return Ledger.buildGetRevocRegDefRequest(submitterDid, id).get()
        }

        actual suspend fun buildGetRevocRegDeltaRequest(
            submitterDid: String,
            revocRegDefId: String,
            from: Long,
            to: Long
        ): String {
            return Ledger.buildGetRevocRegDeltaRequest(submitterDid, revocRegDefId, from, to).get()
        }

        actual suspend fun submitRequest(pool: Pool, requestJson: String): String {
            return Ledger.submitRequest(pool, requestJson).get()
        }

        actual suspend fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult {
            return ParseResponseResult( Ledger.parseGetSchemaResponse(getSchemaResponse).get().objectJson)
        }

        actual fun parseGetCredDefResponse(getCredDefResponse: String): ParseResponseResult {
            return ParseResponseResult(Ledger.parseGetCredDefResponse(getCredDefResponse).get().objectJson)
        }

        actual suspend fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult {
            return ParseResponseResult(Ledger.parseGetRevocRegDefResponse(getRevocRegDefResponse).get().objectJson)
        }

        actual suspend fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult {
            val result = Ledger.parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse).get()
            return ParseRegistryResponseResult(timestamp = result.timestamp, objectJson = result.objectJson)
        }
    }
}
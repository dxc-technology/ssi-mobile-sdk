package com.dxc.ssi.agent.ledger.indy.libindy

expect class Ledger {
    companion object {
        suspend fun buildGetSchemaRequest(submitterDid: String, id: String): String
        suspend fun buildGetCredDefRequest(submitterDid: String, id: String): String
        suspend fun buildGetRevocRegDefRequest(submitterDid: String, id: String): String
        suspend fun buildGetRevocRegDeltaRequest(submitterDid: String, revocRegDefId: String, from: Long, to: Long): String
        suspend fun submitRequest(pool: Pool, requestJson: String): String
        suspend fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult
        suspend fun parseGetCredDefResponse(getCredDefResponse: String): ParseResponseResult
        suspend fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult
        suspend fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult
    }
}
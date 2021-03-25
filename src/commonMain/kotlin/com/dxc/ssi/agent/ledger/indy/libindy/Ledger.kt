package com.dxc.ssi.agent.ledger.indy.libindy

expect class Ledger {
    companion object {
        fun buildGetSchemaRequest(submitterDid: String, id: String): String
        fun buildGetCredDefRequest(submitterDid: String, id: String): String
        fun buildGetRevocRegDefRequest(submitterDid: String, id: String): String
        fun buildGetRevocRegDeltaRequest(submitterDid: String, revocRegDefId: String, from: Long, to: Long): String
        fun submitRequest(pool: Pool, requestJson: String): String
        fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult
        fun parseGetCredDefResponse(getCredDefResponse: String): ParseResponseResult
        fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult
        fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult
    }
}
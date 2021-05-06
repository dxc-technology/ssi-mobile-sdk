package com.dxc.ssi.agent.ledger.indy.libindy

actual class Ledger {
    actual companion object {
        actual fun buildGetSchemaRequest(submitterDid: String, id: String): String {
            TODO("Not yet implemented")
        }

        actual suspend fun buildGetCredDefRequest(submitterDid: String, id: String): String {
            TODO("Not yet implemented")
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
            TODO("Not yet implemented")
        }

        actual fun parseGetSchemaResponse(getSchemaResponse: String): ParseResponseResult {
            TODO("Not yet implemented")
        }

        actual suspend fun parseGetCredDefResponse(getCredDefResponse: String): ParseResponseResult {
            TODO("Not yet implemented")
        }

        actual fun parseGetRevocRegDefResponse(getRevocRegDefResponse: String): ParseResponseResult {
            TODO("Not yet implemented")
        }

        actual fun parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse: String): ParseRegistryResponseResult {
            TODO("Not yet implemented")
        }
    }
}
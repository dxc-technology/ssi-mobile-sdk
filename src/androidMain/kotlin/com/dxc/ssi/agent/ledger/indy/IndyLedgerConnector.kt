package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Schema
import com.dxc.ssi.agent.didcomm.model.verify.data.SchemaId
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinition
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.verify.IndySchema
import com.dxc.ssi.agent.wallet.indy.model.verify.Interval
import com.dxc.ssi.agent.wallet.indy.model.verify.RevocationRegistryEntry
import kotlinx.serialization.decodeFromString
import org.hyperledger.indy.sdk.ledger.Ledger
import org.hyperledger.indy.sdk.pool.Pool
import java.io.File

actual class IndyLedgerConnector actual constructor(indyLedgerConnectorConfiguration: IndyLedgerConnectorConfiguration) : LedgerConnector {
    //TODO: decide if this is proper place for storing did or it should be somewhere in common module and probably out of ledger connector at all , since our did is more than about ledger
    override var did: String = ""


    private val pool: Pool

    init {
        //TODO: think where to store and initialize pool variable
        val genesisFile = File(indyLedgerConnectorConfiguration.genesisFilePath)
        pool = PoolHelper.openOrCreate(genesisFile)
    }

    actual override fun retrieveSchema(id: SchemaId, delayMs: Long, retryTimes: Int): Schema? {
        repeat(retryTimes) {
            try {
                val schemaReq = Ledger.buildGetSchemaRequest(did, id.toString()).get()
                val schemaRes = Ledger.submitRequest(pool, schemaReq).get()
                val parsedRes = Ledger.parseGetSchemaResponse(schemaRes).get()

                println("parsedRes.objectJson = ${parsedRes.objectJson}")

                return jsonProcessor.decodeFromString<IndySchema>(parsedRes.objectJson)
            } catch (e: Exception) {
                println("Schema retrieving failed (id: $id). Retry attempt $it")
                Thread.sleep(delayMs * it)
            }
        }

        return null
    }

    actual override fun retrieveCredentialDefinition(
        id: CredentialDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): CredentialDefinition? {
        repeat(retryTimes) {
            try {
                val getCredDefRequest = Ledger.buildGetCredDefRequest(did, id.toString()).get()
                val getCredDefResponse = Ledger.submitRequest(pool, getCredDefRequest).get()
                val credDefIdInfo = Ledger.parseGetCredDefResponse(getCredDefResponse).get()

                println("retrieved credDefInfo = $credDefIdInfo")
                println("retrieved credDefInfo object JSON = ${credDefIdInfo.objectJson}")

                //TODO: see if we can remove ignoring unknown keys from here
                //TODO: see if "isLenient" is needed here. Maybe instead we can just improve model?
                return jsonProcessor.decodeFromString<IndyCredentialDefinition>(credDefIdInfo.objectJson)

                /*return SerializationUtils.jSONToAny<CredentialDefinition>(
                    credDefIdInfo.objectJson
                )*/
            } catch (e: Exception) {
                //TODO: make retry only ledger related operations, there is no point to retry deserialization
                println("Exception $e")
                println { "Credential definition retrieving failed (id: $id). Retry attempt $it" }

                Thread.sleep(delayMs * it)
            }
        }

        return null
    }

    actual override fun retrieveRevocationRegistryDefinition(
        id: RevocationRegistryDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): RevocationRegistryDefinition? {
        repeat(retryTimes) {
            try {
                val request = Ledger.buildGetRevocRegDefRequest(did, id.toString()).get()
                val response = Ledger.submitRequest(pool, request).get()
                val revRegDefJson = Ledger.parseGetRevocRegDefResponse(response).get().objectJson

                return jsonProcessor.decodeFromString<RevocationRegistryDefinition>(revRegDefJson)

            } catch (e: Exception) {
                println("Revocation registry definition retrieving failed (id: $id). Retry attempt $it")
                Thread.sleep(delayMs * it)
            }
        }

        return null
    }

    actual override fun retrieveRevocationRegistryDelta(
        id: RevocationRegistryDefinitionId,
        interval: Interval,
        delayMs: Long,
        retryTimes: Int
    ): Pair<Long, RevocationRegistryEntry>? {
        repeat(retryTimes) {
            try {
                val from = interval.from
                    ?: -1 // according to https://github.com/hyperledger/indy-sdk/blob/master/libindy/src/api/ledger.rs:1623

                val request = Ledger.buildGetRevocRegDeltaRequest(did, id.toString(), from, interval.to).get()
                val response = Ledger.submitRequest(pool, request).get()
                val revRegDeltaJson = Ledger.parseGetRevocRegDeltaResponse(response).get()

                val timestamp = revRegDeltaJson.timestamp
                val revRegDelta = jsonProcessor.decodeFromString<RevocationRegistryEntry>(revRegDeltaJson.objectJson)

                return Pair(timestamp, revRegDelta)
            } catch (e: Exception) {
                println("Revocation registry delta retrieving failed (id: $id, interval: $interval). Retry attempt $it")
                Thread.sleep(delayMs * it)
            }
        }

        return null
    }


}

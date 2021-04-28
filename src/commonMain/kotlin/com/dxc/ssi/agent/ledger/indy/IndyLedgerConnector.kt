package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Schema
import com.dxc.ssi.agent.didcomm.model.verify.data.SchemaId
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.ledger.indy.libindy.Ledger
import com.dxc.ssi.agent.ledger.indy.libindy.Pool
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinition
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.verify.IndySchema
import com.dxc.ssi.agent.wallet.indy.model.verify.Interval
import com.dxc.ssi.agent.wallet.indy.model.verify.RevocationRegistryEntry
import com.dxc.utils.Sleeper
import kotlinx.serialization.decodeFromString

class IndyLedgerConnector(val indyLedgerConnectorConfiguration: IndyLedgerConnectorConfiguration) : LedgerConnector {
    //TODO: decide if this is proper place for storing did or it should be somewhere in common module and probably out of ledger connector at all , since our did is more than about ledger
    override var did: String = ""
    private lateinit var pool: Pool

    override fun init() {
        //TODO: think where to store and initialize pool variable

        pool = if (indyLedgerConnectorConfiguration.genesisMode == IndyLedgerConnectorConfiguration.GenesisMode.FILE) {
            PoolHelper.openOrCreateFromFilename(indyLedgerConnectorConfiguration.genesisFilePath)
        } else {
            PoolHelper.openOrCreateFromIp(
                indyLedgerConnectorConfiguration.ipAddress,
                indyLedgerConnectorConfiguration.dirForGeneratedGenesis
            )
        }
    }


    override fun retrieveSchema(id: SchemaId, delayMs: Long, retryTimes: Int): Schema? {
        repeat(retryTimes) {
            try {
                val schemaReq = Ledger.buildGetSchemaRequest(did, id.toString())
                val schemaRes = Ledger.submitRequest(pool, schemaReq)
                val parsedRes = Ledger.parseGetSchemaResponse(schemaRes)

                println("parsedRes.objectJson = ${parsedRes.objectJson}")

                return IndySerializationUtils.jsonProcessor.decodeFromString<IndySchema>(parsedRes.objectJson)
            } catch (e: Exception) {
                println("Schema retrieving failed (id: $id). Retry attempt $it")
                Sleeper().sleep((delayMs * it) as Int)
            }
        }

        return null
    }

    override fun retrieveCredentialDefinition(
        id: CredentialDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): CredentialDefinition? {
        repeat(retryTimes) {
            try {
                val getCredDefRequest = Ledger.buildGetCredDefRequest(did, id.toString())
                val getCredDefResponse = Ledger.submitRequest(pool, getCredDefRequest)
                val credDefIdInfo = Ledger.parseGetCredDefResponse(getCredDefResponse)

                println("retrieved credDefInfo = $credDefIdInfo")
                println("retrieved credDefInfo object JSON = ${credDefIdInfo.objectJson}")

                //TODO: see if we can remove ignoring unknown keys from here
                //TODO: see if "isLenient" is needed here. Maybe instead we can just improve model?
                return IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredentialDefinition>(credDefIdInfo.objectJson)

                /*return SerializationUtils.jSONToAny<CredentialDefinition>(
                    credDefIdInfo.objectJson
                )*/
            } catch (e: Exception) {
                //TODO: make retry only ledger related operations, there is no point to retry deserialization
                println("Exception $e")
                println { "Credential definition retrieving failed (id: $id). Retry attempt $it" }

                Sleeper().sleep((delayMs * it) as Int)
            }
        }

        return null
    }

    override fun retrieveRevocationRegistryDefinition(
        id: RevocationRegistryDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): RevocationRegistryDefinition? {
        repeat(retryTimes) {
            try {
                val request = Ledger.buildGetRevocRegDefRequest(did, id.toString())
                val response = Ledger.submitRequest(pool, request)
                val revRegDefJson = Ledger.parseGetRevocRegDefResponse(response).objectJson

                return IndySerializationUtils.jsonProcessor.decodeFromString<RevocationRegistryDefinition>(revRegDefJson)

            } catch (e: Exception) {
                println("Revocation registry definition retrieving failed (id: $id). Retry attempt $it")
                Sleeper().sleep((delayMs * it) as Int)
            }
        }

        return null
    }

    override fun retrieveRevocationRegistryDelta(
        id: RevocationRegistryDefinitionId,
        interval: Interval,
        delayMs: Long,
        retryTimes: Int
    ): Pair<Long, RevocationRegistryEntry>? {
        repeat(retryTimes) {
            try {
                val from = interval.from
                    ?: -1 // according to https://github.com/hyperledger/indy-sdk/blob/master/libindy/src/api/ledger.rs:1623

                val request = Ledger.buildGetRevocRegDeltaRequest(did, id.toString(), from, interval.to)
                val response = Ledger.submitRequest(pool, request)
                val revRegDeltaJson = Ledger.parseGetRevocRegDeltaResponse(response)

                val timestamp = revRegDeltaJson.timestamp
                val revRegDelta =
                    IndySerializationUtils.jsonProcessor.decodeFromString<RevocationRegistryEntry>(revRegDeltaJson.objectJson)

                return Pair(timestamp, revRegDelta)
            } catch (e: Exception) {
                println("Revocation registry delta retrieving failed (id: $id, interval: $interval). Retry attempt $it")
                Sleeper().sleep((delayMs * it) as Int)
            }
        }

        return null
    }


}

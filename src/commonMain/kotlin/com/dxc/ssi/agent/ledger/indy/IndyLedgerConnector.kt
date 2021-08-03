package com.dxc.ssi.agent.ledger.indy

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Severity
import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Schema
import com.dxc.ssi.agent.didcomm.model.verify.data.SchemaId
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.ledger.indy.libindy.Ledger
import com.dxc.ssi.agent.ledger.indy.libindy.Pool
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinition
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.verify.IndySchema
import com.dxc.ssi.agent.wallet.indy.model.verify.Interval
import com.dxc.ssi.agent.wallet.indy.model.verify.RevocationRegistryEntry
import com.dxc.utils.EnvironmentUtils
import com.dxc.utils.Sleeper
import kotlinx.serialization.decodeFromString

class IndyLedgerConnector internal constructor(val indyLedgerConnectorConfiguration: IndyLedgerConnectorConfiguration) : LedgerConnector {
    private val logger: Kermit = Kermit(LogcatLogger())
    //TODO: decide if this is proper place for storing did or it should be somewhere in common module and probably out of ledger connector at all , since our did is more than about ledger
    override var did: String
        get() = isoDid.access { it.obj }!!
        set(value) {
            isoDid.access { it.obj = value }
        }
    private val isoDid = IsolateState { ObjectHolder<String>() }

    private var isoPool = IsolateState { ObjectHolder<Pool>() }

    override suspend fun init() {
        //TODO: think where to store and initialize pool variable
        try {
            val pool =
                if (indyLedgerConnectorConfiguration.genesisMode == GenesisMode.FILE) {
                    PoolHelper.openOrCreateFromFilename(indyLedgerConnectorConfiguration.genesisFilePath)
                } else {
                    PoolHelper.recreateCustomGenesis(
                        indyLedgerConnectorConfiguration.genesisMode,
                        indyLedgerConnectorConfiguration.ipAddress,
                        EnvironmentUtils.writableUserHomePath,
                        indyLedgerConnectorConfiguration.generatedGenesysFileName
                    )
                }
            isoPool.access { it.obj = pool }
        } catch (e: Exception) {
            logger.log(Severity.Error,"",null) { "An issue occurred: $e" }
        }
    }


    override suspend fun retrieveSchema(id: SchemaId): Schema? {
        repeat(indyLedgerConnectorConfiguration.retryTimes) {
            try {

                val pool = isoPool.access { it.obj }
                val schemaReq = Ledger.buildGetSchemaRequest(did, id.toString())
                val schemaRes = Ledger.submitRequest(pool!!, schemaReq)
                val parsedRes = Ledger.parseGetSchemaResponse(schemaRes)

                logger.log(Severity.Debug,"",null) { "parsedRes.objectJson = ${parsedRes.objectJson}" }

                return IndySerializationUtils.jsonProcessor.decodeFromString<IndySchema>(parsedRes.objectJson)
            } catch (e: Exception) {
                logger.log(Severity.Error,"",null) { "Schema retrieving failed (id: $id). Retry attempt $it" }
                Sleeper().sleep((indyLedgerConnectorConfiguration.retryDelayMs * it) as Int)
            }
        }

        return null
    }

    override suspend fun retrieveCredentialDefinition(
        id: CredentialDefinitionId
    ): CredentialDefinition? {
        repeat(indyLedgerConnectorConfiguration.retryTimes) {
            try {
                val pool = isoPool.access { it.obj }
                val getCredDefRequest = Ledger.buildGetCredDefRequest(did, id.toString())
                val getCredDefResponse = Ledger.submitRequest(pool!!, getCredDefRequest)
                val credDefIdInfo = Ledger.parseGetCredDefResponse(getCredDefResponse)

                logger.log(Severity.Debug,"",null) { "retrieved credDefInfo = $credDefIdInfo" }
                logger.log(Severity.Debug,"",null) { "retrieved credDefInfo object JSON = ${credDefIdInfo.objectJson}" }

                //TODO: see if we can remove ignoring unknown keys from here
                //TODO: see if "isLenient" is needed here. Maybe instead we can just improve model?
                return IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredentialDefinition>(credDefIdInfo.objectJson)

                /*return SerializationUtils.jSONToAny<CredentialDefinition>(
                    credDefIdInfo.objectJson
                )*/
            } catch (e: Exception) {
                //TODO: make retry only ledger related operations, there is no point to retry deserialization

                logger.log(Severity.Error,"",null) { "Exception $e" }
                logger.log(Severity.Error,"",null) { "Credential definition retrieving failed (id: $id). Retry attempt $it" }

                Sleeper().sleep((indyLedgerConnectorConfiguration.retryDelayMs * it) as Int)
            }
        }

        return null
    }

    override suspend fun retrieveRevocationRegistryDefinition(
        id: RevocationRegistryDefinitionId
    ): RevocationRegistryDefinition? {
        repeat(indyLedgerConnectorConfiguration.retryTimes) {
            try {
                val pool = isoPool.access { it.obj }
                val request = Ledger.buildGetRevocRegDefRequest(did, id.toString())
                val response = Ledger.submitRequest(pool!!, request)
                val revRegDefJson = Ledger.parseGetRevocRegDefResponse(response).objectJson

                return IndySerializationUtils.jsonProcessor.decodeFromString<RevocationRegistryDefinition>(revRegDefJson)

            } catch (e: Exception) {
                logger.log(Severity.Error,"",null) { "Revocation registry definition retrieving failed (id: $id). Retry attempt $it" }
                Sleeper().sleep((indyLedgerConnectorConfiguration.retryDelayMs * it) as Int)
            }
        }

        return null
    }

    override suspend fun retrieveRevocationRegistryDelta(
        id: RevocationRegistryDefinitionId,
        interval: Interval
    ): Pair<Long, RevocationRegistryEntry>? {
        repeat(indyLedgerConnectorConfiguration.retryTimes) {
            try {
                val pool = isoPool.access { it.obj }
                val from = interval.from
                    ?: -1 // according to https://github.com/hyperledger/indy-sdk/blob/master/libindy/src/api/ledger.rs:1623

                val request = Ledger.buildGetRevocRegDeltaRequest(did, id.toString(), from, interval.to)
                val response = Ledger.submitRequest(pool!!, request)
                val revRegDeltaJson = Ledger.parseGetRevocRegDeltaResponse(response)

                val timestamp = revRegDeltaJson.timestamp
                val revRegDelta =
                    IndySerializationUtils.jsonProcessor.decodeFromString<RevocationRegistryEntry>(revRegDeltaJson.objectJson)

                return Pair(timestamp, revRegDelta)
            } catch (e: Exception) {
                logger.log(Severity.Error,"",null) { "Revocation registry delta retrieving failed (id: $id, interval: $interval). Retry attempt $it" }
                Sleeper().sleep((indyLedgerConnectorConfiguration.retryDelayMs * it) as Int)
            }
        }

        return null
    }


}

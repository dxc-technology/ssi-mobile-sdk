package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.ledger.indy.helpers.PoolHelper
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.issue.IndyCredentialDefinition
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

}

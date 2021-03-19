package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Schema
import com.dxc.ssi.agent.didcomm.model.verify.data.SchemaId
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.verify.Interval
import com.dxc.ssi.agent.wallet.indy.model.verify.RevocationRegistryEntry

expect class IndyLedgerConnector() : LedgerConnector {

    override fun retrieveCredentialDefinition(
        id: CredentialDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): CredentialDefinition?

    override fun retrieveSchema(
        id: SchemaId,
        delayMs: Long,
        retryTimes: Int
    ): Schema?

    override fun retrieveRevocationRegistryDefinition(
        id: RevocationRegistryDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): RevocationRegistryDefinition?

    override fun retrieveRevocationRegistryDelta(
        id: RevocationRegistryDefinitionId,
        interval: Interval,
        delayMs: Long,
        retryTimes: Int
    ): Pair<Long, RevocationRegistryEntry>?

}

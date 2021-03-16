package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId

actual class IndyLedgerConnector actual constructor(indyLedgerConnectorConfiguration: IndyLedgerConnectorConfiguration) : LedgerConnector {
    override var did: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun retrieveCredentialDefinition(
        id: CredentialDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): CredentialDefinition? {
        TODO("Not yet implemented")
    }


}
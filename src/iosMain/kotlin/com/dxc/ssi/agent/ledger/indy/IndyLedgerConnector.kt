package com.dxc.ssi.agent.ledger.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinition
import com.dxc.ssi.agent.didcomm.model.issue.data.CredentialDefinitionId

actual class IndyLedgerConnector : LedgerConnector {
    override val did: String
        get() = TODO("Not yet implemented")

    actual override fun retrieveCredentialDefinition(
        id: CredentialDefinitionId,
        delayMs: Long,
        retryTimes: Int
    ): CredentialDefinition? {
        TODO("Not yet implemented")
    }

}

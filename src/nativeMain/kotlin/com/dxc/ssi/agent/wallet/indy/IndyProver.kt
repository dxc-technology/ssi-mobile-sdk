package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.Data
import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.model.IdentityDetails

actual class IndyProver actual constructor(walletHolder: WalletHolder) : IndyWalletHolder(), Prover {
    actual override fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        credentialOffer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo {
        TODO("Not yet implemented")
    }

    actual override fun storeCredentialExchangeRecord(credentialExchangeRecord: CredentialExchangeRecord) {
        TODO("Not yet implemented")
    }

    actual override fun getCredentialExchangeRecordByThread(thread: Thread): CredentialExchangeRecord? {
        TODO("Not yet implemented")
    }

    actual override fun createCredentialDefinitionIdFromOffer(credentialOffer: CredentialOffer): CredentialDefinitionId {
        TODO("Not yet implemented")
    }

    actual override fun receiveCredential(
        credential: Credential,
        credentialRequestInfo: CredentialRequestInfo,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String {
        TODO("Not yet implemented")
    }

    actual override fun createMasterSecret(id: String) {
        TODO("Not yet implemented")
    }

    actual override fun extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo: CredentialRequestInfo): Data {
        TODO("Not yet implemented")
    }

    actual override fun buildCredentialObjectFromRawData(data: Data): Credential {
        TODO("Not yet implemented")
    }

    actual override fun buildCredentialOfferObjectFromRawData(data: Data): CredentialOffer {
        TODO("Not yet implemented")
    }

    actual override fun removeCredentialExchangeRecordByThread(thread: Thread) {
        TODO("Not yet implemented")
    }


}

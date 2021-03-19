package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.didcomm.model.common.Data
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Presentation
import com.dxc.ssi.agent.didcomm.model.verify.data.PresentationRequest
import com.dxc.ssi.agent.model.CredentialExchangeRecord

//TODO: consider going deeper and expect not our classes but underlying library instead. I.e. make this class common  but implement Wallet, WalletRecord etc, classes from the library
expect class IndyProver(walletHolder: WalletHolder) : Prover {
    override fun createMasterSecret(id: String)
    override fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        credentialOffer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo

    override fun createCredentialDefinitionIdFromOffer(credentialOffer: CredentialOffer): CredentialDefinitionId

    override fun storeCredentialExchangeRecord(credentialExchangeRecord: CredentialExchangeRecord)
    override fun getCredentialExchangeRecordByThread(thread: Thread): CredentialExchangeRecord?

    override fun receiveCredential(
        credential: Credential,
        credentialRequestInfo: CredentialRequestInfo,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String


    override fun extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo: CredentialRequestInfo): Data
    override fun buildCredentialObjectFromRawData(data: Data): Credential
    override fun buildCredentialOfferObjectFromRawData(data: Data): CredentialOffer
    override fun removeCredentialExchangeRecordByThread(thread: Thread)
    override fun buildPresentationRequestObjectFromRawData(data: Data): PresentationRequest
    override fun createPresentation(
        presentationRequest: PresentationRequest,
        ledgerConnector: LedgerConnector,
    ): Presentation

    override fun extractPresentationDataFromPresentation(presentation: Presentation): Data

}

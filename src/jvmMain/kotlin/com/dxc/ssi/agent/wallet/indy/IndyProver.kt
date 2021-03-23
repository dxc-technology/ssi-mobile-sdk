package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.LedgerConnector
import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.config.Configuration
import com.dxc.ssi.agent.didcomm.model.common.Data
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.didcomm.model.verify.data.Presentation
import com.dxc.ssi.agent.didcomm.model.verify.data.PresentationRequest
import com.dxc.ssi.agent.ledger.indy.helpers.TailsHelper
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.utils.JsonUtils.extractValue
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import com.dxc.ssi.agent.wallet.indy.model.issue.*
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.revoke.IndyRevocationRegistryDefinition
import com.dxc.ssi.agent.wallet.indy.model.revoke.RevocationState
import com.dxc.ssi.agent.wallet.indy.model.verify.*
import com.fasterxml.jackson.databind.util.ClassUtil.getRootCause
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.anoncreds.CredentialsSearchForProofReq
import org.hyperledger.indy.sdk.anoncreds.DuplicateMasterSecretNameException
import org.hyperledger.indy.sdk.non_secrets.WalletRecord
import org.hyperledger.indy.sdk.wallet.Wallet
import org.hyperledger.indy.sdk.wallet.WalletItemNotFoundException
import java.util.*
import java.util.concurrent.ExecutionException

actual class IndyProver actual constructor(private val walletHolder: WalletHolder) : Prover {

    private var masterSecretId: String? = null

    private val tailsPath = Configuration.tailsPath

    actual override fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        credentialOffer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo {
        val credentialOfferJson = jsonProcessor.encodeToString(credentialOffer)

        val credDefJson = jsonProcessor.encodeToString(credentialDefinition)

        println(
            "Before executing Anoncreds.proverCreateCredentialReq " +
                    "proverDid = $proverDid," +
                    "credentialOfferJson = $credentialOfferJson," +
                    "credDefJson = $credDefJson," +
                    "masterSecretId = $masterSecretId"
        )

        val credReq = Anoncreds.proverCreateCredentialReq(
            walletHolder.getWallet() as Wallet, proverDid, credentialOfferJson, credDefJson, masterSecretId
        ).get()

        //TODO: see if we need to leave it here for kind of validation purposes...
        //val indyCredentialRequest = SerializationUtils.jSONToAny<CredentialRequest>(credReq.credentialRequestJson)

        //TODO: see if we need to return this metadata at all
        /*  val credentialRequestMetadata =
            SerializationUtils.jSONToAny<CredentialRequestMetadata>(credReq.credentialRequestMetadataJson)
            */

        //indyCredentialRequest

        println("credReq.credentialRequestJson = ${credReq.credentialRequestJson}")
        println("credReq.credentialRequestMetadataJson = ${credReq.credentialRequestMetadataJson}")

        val credentialRequest = jsonProcessor.decodeFromString<IndyCredentialRequest>(credReq.credentialRequestJson)
        val credentialRequestMetadata =
            jsonProcessor.decodeFromString<IndyCredentialRequestMetadata>(credReq.credentialRequestMetadataJson)

        return CredentialRequestInfo(credentialRequest, credentialRequestMetadata)

    }

    actual override fun createMasterSecret(id: String) {
        this.masterSecretId = id
        try {
            Anoncreds.proverCreateMasterSecret(walletHolder.getWallet() as Wallet, id).get()
        } catch (e: ExecutionException) {
            if (getRootCause(e) !is DuplicateMasterSecretNameException) throw e

            //TODO: think what should be the behaviour
            println("MasterSecret already exists, who cares, continuing")
        }
    }

    actual override fun createCredentialDefinitionIdFromOffer(credentialOffer: CredentialOffer): CredentialDefinitionId {

        println("createCredentialDefinitionIdFromOffer: cred offer ${credentialOffer}")

        val indyCredentialOffer = credentialOffer as IndyCredentialOffer


        println("indy cred offer ${indyCredentialOffer}")

        val credentialDefinitionIdRaw = indyCredentialOffer.credentialDefinitionIdRaw

        val strSplitted = credentialDefinitionIdRaw.split(":")
        val didCred = strSplitted[0]
        val tag = strSplitted[strSplitted.lastIndex]
        val seqNo = strSplitted[3].toInt()

        return IndyCredentialDefinitionId(didCred, seqNo, tag)

    }

    actual override fun storeCredentialExchangeRecord(credentialExchangeRecord: CredentialExchangeRecord) {
        //TODO: check if we need to check wallet health status before using it

        //TODO:Understand what id to use for uniquely identifying proper record
        val existingCredentialExchangeRecord = getCredentialExchangeRecordByThread(credentialExchangeRecord.thread)


        val value = jsonProcessor.encodeToString(credentialExchangeRecord)

        println("Serialized credentialExchange record = $value")

        if (existingCredentialExchangeRecord == null) {

            //TODO: think what tags do we need here
            val tagsJson = null

            WalletRecord.add(
                walletHolder.getWallet() as Wallet,
                WalletRecordType.CredentialExchangeRecord.name,
                credentialExchangeRecord.thread.thid,
                value,
                tagsJson
            ).get()

        } else {

            //TODO: see if we also need to update tags

            WalletRecord.updateValue(
                walletHolder.getWallet() as Wallet,
                WalletRecordType.CredentialExchangeRecord.name,
                credentialExchangeRecord.thread.thid,
                value
            )


        }
    }

    actual override fun getCredentialExchangeRecordByThread(thread: Thread): CredentialExchangeRecord? {

        //TODO: use some serializable data structure
        val options = "{\"retrieveType\" : true}"
        /*
        *  retrieveType: (optional, false by default) Retrieve record type,
        * retrieveValue: (optional, true by default) Retrieve record value,
        * retrieveTags: (optional, true by default) Retrieve record tags }
        *
        * */


        //TODO: find out better solution of looking up for connection
        return try {
            val retrievedValue =
                WalletRecord.get(
                    walletHolder.getWallet() as Wallet,
                    WalletRecordType.CredentialExchangeRecord.name,
                    thread.thid,
                    options
                ).get()
            jsonProcessor.decodeFromString<CredentialExchangeRecord>(extractValue(retrievedValue))
        } catch (e: ExecutionException) {
            if (e.cause is WalletItemNotFoundException)
                null
            else
                throw e
        }

    }

    actual override fun extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo: CredentialRequestInfo): Data {

        //TODO: check if this type cast is needed here
        val credentialRequestJson =
            jsonProcessor.encodeToString(credentialRequestInfo.credentialRequest as IndyCredentialRequest)
        println("extractCredentialRequestDataFromCredentialInfo: credentialRequestJson = $credentialRequestJson")


        return Data(base64 = Base64.getEncoder().encodeToString(credentialRequestJson.toByteArray()))
    }


    actual override fun receiveCredential(
        credential: Credential,
        credentialRequestInfo: CredentialRequestInfo,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String {

        val credentialJson = jsonProcessor.encodeToString(credential)
        val credentialRequestMetadataJson =
            jsonProcessor.encodeToString(credentialRequestInfo.credentialRequestMetadata)
        val credDefJson = jsonProcessor.encodeToString(credentialDefinition)
        val revRegDefJson =
            if (revocationRegistryDefinition == null)
                null
            else
                jsonProcessor.encodeToString(revocationRegistryDefinition)

        println("receiveCredential: credentialRequestMetadataJson -> $credentialRequestMetadataJson")
        println("receiveCredential: credentialJson -> $credentialJson")
        println("receiveCredential: credDefJson -> $credDefJson")
        println("receiveCredential: revRegDefJson -> $revRegDefJson")

        return Anoncreds.proverStoreCredential(
            walletHolder.getWallet() as Wallet,
            null,
            credentialRequestMetadataJson,
            credentialJson,
            credDefJson,
            revRegDefJson
        ).get()

    }

    override fun createRevocationState(
        revocationRegistryDefinition: RevocationRegistryDefinition,
        revocationRegistryEntry: RevocationRegistryEntry,
        credentialRevocationId: String,
        timestamp: Long
    ): RevocationState {

        val indyRevocationRegistryDefinition = revocationRegistryDefinition as IndyRevocationRegistryDefinition

        val tailsReaderHandle = TailsHelper.getTailsHandler(tailsPath).reader.blobStorageReaderHandle

        val revRegDefJson = jsonProcessor.encodeToString(revocationRegistryDefinition)

        val revRegDeltaJson = jsonProcessor.encodeToString(revocationRegistryEntry)


        val revStateJson = Anoncreds.createRevocationState(
            tailsReaderHandle,
            revRegDefJson,
            revRegDeltaJson,
            timestamp,
            credentialRevocationId
        ).get()

        val revocationState = jsonProcessor.decodeFromString<RevocationState>(revStateJson)
        revocationState.revocationRegistryIdRaw = indyRevocationRegistryDefinition.id

        return revocationState
    }

    actual override fun buildCredentialObjectFromRawData(data: Data): Credential {

        val indyCredentialJson = String(Base64.getMimeDecoder().decode(data.base64))

        val indyCredential =
            jsonProcessor.decodeFromString<IndyCredential>(indyCredentialJson)

        return indyCredential

    }

    actual override fun buildCredentialOfferObjectFromRawData(data: Data): CredentialOffer {

        val jsonCredentialOffer = String(
            Base64.getMimeDecoder()
                .decode(data.base64)
        )

        println("jsonCredentialOffer = $jsonCredentialOffer")

        val indyCredentialOffer =
            jsonProcessor.decodeFromString<IndyCredentialOffer>(
                jsonCredentialOffer
            )

        return indyCredentialOffer

    }

    actual override fun removeCredentialExchangeRecordByThread(thread: Thread) {

        WalletRecord.delete(
            walletHolder.getWallet() as Wallet,
            WalletRecordType.CredentialExchangeRecord.name,
            thread.thid
        ).get()
    }

    actual override fun buildPresentationRequestObjectFromRawData(data: Data): PresentationRequest {
        val indyPresentationRequestJson = String(Base64.getMimeDecoder().decode(data.base64))

        println("Received JSON PresentationRequest: $indyPresentationRequestJson")

        val indyPresentationReuqest =
            jsonProcessor.decodeFromString<IndyPresentationRequest>(indyPresentationRequestJson)

        return indyPresentationReuqest
    }

    actual override fun createPresentation(
        presentationRequest: PresentationRequest,
        ledgerConnector: LedgerConnector,
        /* TODO: add extra query parameter
        extraQuery: Map<String, Map<String, Any>>?*/
    ): Presentation {

        val indyPresentationRequest = presentationRequest as IndyPresentationRequest

        val proofRequestJson = jsonProcessor.encodeToString(presentationRequest)

        println("In createPresentation function: proofRequestJson = $proofRequestJson")

        //TODO: deal with extra query. Understand what it is and how to use it. See cordentity
        val extraQueryJson = null

        val searchObj =
            CredentialsSearchForProofReq.open(walletHolder.getWallet() as Wallet, proofRequestJson, extraQueryJson)
                .get()

        val allSchemaIds = mutableListOf<IndySchemaId>()
        val allCredentialDefinitionIds = mutableListOf<IndyCredentialDefinitionId>()
        val allRevStates = mutableListOf<RevocationState>()

        //TODO: remove copypaste code from requestedAttributes and requestedPredicates
        val requestedAttributes = indyPresentationRequest.requestedAttributes.keys.associate { key ->

            val credentialJson = searchObj.fetchNextCredentials(key, 1).get()

            println("Retrieved for key = $key  -> credentialJson: $credentialJson")

            val credentialForTheRequest =
                jsonProcessor.decodeFromString<List<IndyCredentialForTheRequest>>(credentialJson)
                    .firstOrNull()
                    ?: throw RuntimeException("Unable to find attribute $key that satisfies proof request: ${indyPresentationRequest.requestedAttributes[key]}")

            allSchemaIds.add(IndySchemaId.fromString(credentialForTheRequest.credInfo.schemaId))
            allCredentialDefinitionIds.add(IndyCredentialDefinitionId.fromString(credentialForTheRequest.credInfo.credDefId))


            val revStateAlreadyDone =
                allRevStates.find { it.revocationRegistryIdRaw == credentialForTheRequest.credInfo.revRegId }

            if (revStateAlreadyDone != null)
                return@associate key to RequestedAttributeInfo(
                    credentialForTheRequest.credInfo.referent,
                    timestamp = revStateAlreadyDone.timestamp
                )

            if ((credentialForTheRequest.credInfo.credRevId == null) xor (indyPresentationRequest.nonRevoked == null))
                throw RuntimeException("If credential is issued using some revocation registry, it should be proved to be non-revoked")

            // if everything is ok and rev state is needed - pull it from ledger
            val requestedAttributeInfo = if (
                credentialForTheRequest.credInfo.credRevId != null &&
                credentialForTheRequest.credInfo.revRegId != null &&
                indyPresentationRequest.nonRevoked != null
            ) {
                val revocationState = provideRevocationState(
                    RevocationRegistryDefinitionId.fromString(credentialForTheRequest.credInfo.revRegId),
                    credentialForTheRequest.credInfo.credRevId,
                    indyPresentationRequest.nonRevoked,
                    ledgerConnector
                )

                allRevStates.add(revocationState)

                RequestedAttributeInfo(
                    credentialForTheRequest.credInfo.referent,
                    timestamp = revocationState.timestamp
                )
            } else { // else just give up
                RequestedAttributeInfo(credentialForTheRequest.credInfo.referent)
            }

            //key to requestedAttributeInfo
            key to requestedAttributeInfo
        }

        val requestedPredicates = indyPresentationRequest.requestedPredicates.keys.associate { key ->
            val credentialJson = searchObj.fetchNextCredentials(key, 1).get()

            println("Retrieved for key = $key  -> credentialJson: $credentialJson")

            val credentialForTheRequest =
                jsonProcessor.decodeFromString<List<IndyCredentialForTheRequest>>(credentialJson)
                    .firstOrNull()
                    ?: throw RuntimeException("Unable to find attribute $key that satisfies proof request: ${indyPresentationRequest.requestedAttributes[key]}")

            allSchemaIds.add(IndySchemaId.fromString(credentialForTheRequest.credInfo.schemaId))
            allCredentialDefinitionIds.add(IndyCredentialDefinitionId.fromString(credentialForTheRequest.credInfo.credDefId))

            val revStateAlreadyDone =
                allRevStates.find { it.revocationRegistryIdRaw == credentialForTheRequest.credInfo.revRegId }

            if (revStateAlreadyDone != null)
                return@associate key to RequestedPredicateInfo(
                    credentialForTheRequest.credInfo.referent,
                    revStateAlreadyDone.timestamp
                )

            // if everything is ok and rev state is needed - pull it from ledger
            val requestedPredicateInfo = if (
                credentialForTheRequest.credInfo.credRevId != null &&
                credentialForTheRequest.credInfo.revRegId != null &&
                presentationRequest.nonRevoked != null
            ) {
                val revocationState = provideRevocationState(
                    RevocationRegistryDefinitionId.fromString(credentialForTheRequest.credInfo.revRegId),
                    credentialForTheRequest.credInfo.credRevId,
                    presentationRequest.nonRevoked,
                    ledgerConnector
                )

                allRevStates.add(revocationState)

                RequestedPredicateInfo(
                    credentialForTheRequest.credInfo.referent,
                    revocationState.timestamp
                )
            } else { // else just give up
                RequestedPredicateInfo(credentialForTheRequest.credInfo.referent, null)
            }

            key to requestedPredicateInfo
        }

        searchObj.closeSearch().get()

        val requestedCredentials = RequestedCredentials(requestedAttributes, requestedPredicates, mapOf())
        val requestedCredentialsJson = jsonProcessor.encodeToString(requestedCredentials)

        val allSchemas = allSchemaIds.distinct().map { ledgerConnector.retrieveSchema(it) as IndySchema }
        val allCredentialDefs = allCredentialDefinitionIds.distinct()
            .map { ledgerConnector.retrieveCredentialDefinition(it) as IndyCredentialDefinition }

        val usedSchemas = allSchemas.associate { it.id to it }
        val usedCredentialDefs = allCredentialDefs.associate { it.id to it }

        val usedRevocationStates = allRevStates
            .associate {
                val stateByTimestamp = hashMapOf<Long, RevocationState>()
                stateByTimestamp[it.timestamp] = it

                it.revocationRegistryIdRaw!! to stateByTimestamp
            }

        val usedSchemasJson = jsonProcessor.encodeToString(usedSchemas)
        val usedCredentialDefsJson = jsonProcessor.encodeToString(usedCredentialDefs)
        val usedRevStatesJson = jsonProcessor.encodeToString(usedRevocationStates)


        println("proofRequestJson -> $proofRequestJson")
        println("requestedCredentialsJson -> $requestedCredentialsJson")
        println("masterSecretId -> $masterSecretId")
        println("usedSchemasJson -> $usedSchemasJson")
        println("usedCredentialDefsJson -> $usedCredentialDefsJson")
        println("usedRevStatesJson -> $usedRevStatesJson")

        val proverProofJson = Anoncreds.proverCreateProof(
            walletHolder.getWallet() as Wallet,
            proofRequestJson,
            requestedCredentialsJson,
            masterSecretId,
            usedSchemasJson,
            usedCredentialDefsJson,
            usedRevStatesJson
        ).get()

        println("Indy proof created: $proverProofJson")

        val presentation = jsonProcessor.decodeFromString<IndyPresentation>(proverProofJson)

        return presentation
    }

    private fun provideRevocationState(
        revRegId: RevocationRegistryDefinitionId,
        credRevId: String,
        interval: Interval,
        ledgerConnector: LedgerConnector
    ): RevocationState {
        val revocationRegistryDefinition = ledgerConnector.retrieveRevocationRegistryDefinition(revRegId)
            ?: throw IndyRevRegNotFoundException(revRegId, "Get revocation state has been failed")

        val response = ledgerConnector.retrieveRevocationRegistryDelta(revRegId, Interval(null, interval.to))
            ?: throw IndyRevDeltaNotFoundException(revRegId, "Interval is $interval")
        val (timestamp, revRegDelta) = response

        val revocationState = createRevocationState(revocationRegistryDefinition, revRegDelta, credRevId, timestamp)

        return revocationState

    }

    actual override fun extractPresentationDataFromPresentation(presentation: Presentation): Data {

        //TODO: check if this type cast is needed here
        val presentationJson =
            jsonProcessor.encodeToString(presentation as IndyPresentation)
        println("extractPresentationDataFromPresentation: presentationJson = $presentationJson")


        return Data(base64 = Base64.getEncoder().encodeToString(presentationJson.toByteArray()))

    }

}

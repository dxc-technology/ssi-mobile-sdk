package com.dxc.ssi.agent.wallet.indy

import co.touchlab.stately.isolate.IsolateState
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
import com.dxc.ssi.agent.exceptions.indy.DuplicateMasterSecretNameException
import com.dxc.ssi.agent.exceptions.indy.WalletItemNotFoundException
import com.dxc.ssi.agent.ledger.indy.helpers.TailsHelper
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.utils.JsonUtils
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.libindy.Anoncreds
import com.dxc.ssi.agent.wallet.indy.libindy.CredentialsSearchForProofReq
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.libindy.WalletRecord
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import com.dxc.ssi.agent.wallet.indy.model.issue.*
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.RevocationRegistryDefinitionId
import com.dxc.ssi.agent.wallet.indy.model.revoke.IndyRevocationRegistryDefinition
import com.dxc.ssi.agent.wallet.indy.model.revoke.RevocationState
import com.dxc.ssi.agent.wallet.indy.model.verify.*
import com.dxc.utils.Base64
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString


class IndyProver(val walletHolder: WalletHolder) : Prover {
    private var masterSecretId: String?
        get() = isoMasterSecret.access { it.obj }!!
        set(value) {
            isoMasterSecret.access { it.obj = value }
        }

    private val isoMasterSecret = IsolateState { ObjectHolder<String>() }

    private val tailsPath = Configuration.tailsPath

    override suspend fun createCredentialRequest(
        proverDid: String,
        credentialDefinition: CredentialDefinition,
        credentialOffer: CredentialOffer,
        masterSecretId: String
    ): CredentialRequestInfo {
        val credentialOfferJson = IndySerializationUtils.jsonProcessor.encodeToString(
            PolymorphicSerializer(CredentialOffer::class),
            credentialOffer
        )

        val credDefJson = IndySerializationUtils.jsonProcessor.encodeToString(
            PolymorphicSerializer(CredentialDefinition::class),
            credentialDefinition
        )

        println(
            "Before executing Anoncreds.proverCreateCredentialReq " +
                    "proverDid = $proverDid," +
                    "credentialOfferJson = $credentialOfferJson," +
                    "credDefJson = $credDefJson," +
                    "masterSecretId = $masterSecretId"
        )

        val credReq = Anoncreds.proverCreateCredentialReq(
            walletHolder.getWallet() as Wallet, proverDid, credentialOfferJson, credDefJson, masterSecretId
        )

        println("credReq.credentialRequestJson = ${credReq.getCredentialRequestJson()}")
        println("credReq.credentialRequestMetadataJson = ${credReq.getCredentialRequestMetadataJson()}")

        val credentialRequest =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredentialRequest>(credReq.getCredentialRequestJson())
        val credentialRequestMetadata =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredentialRequestMetadata>(credReq.getCredentialRequestMetadataJson())

        return CredentialRequestInfo(credentialRequest, credentialRequestMetadata)

    }

    override suspend fun createMasterSecret(id: String) {
        masterSecretId = id
        try {
            Anoncreds.proverCreateMasterSecret(walletHolder.getWallet() as Wallet, id)
        } catch (e: DuplicateMasterSecretNameException) {
            println("MasterSecret already exists, so we will use it")
        }
    }

    override fun createCredentialDefinitionIdFromOffer(credentialOffer: CredentialOffer): CredentialDefinitionId {

        println("createCredentialDefinitionIdFromOffer: cred offer $credentialOffer")

        val indyCredentialOffer = credentialOffer as IndyCredentialOffer


        println("indy cred offer ${indyCredentialOffer}")

        val credentialDefinitionIdRaw = indyCredentialOffer.credentialDefinitionIdRaw

        val strSplitted = credentialDefinitionIdRaw.split(":")
        val didCred = strSplitted[0]
        val tag = strSplitted[strSplitted.lastIndex]
        val seqNo = strSplitted[3].toInt()

        return IndyCredentialDefinitionId(didCred, seqNo, tag)

    }

    override suspend fun storeCredentialExchangeRecord(credentialExchangeRecord: CredentialExchangeRecord) {
        //TODO: check if we need to check wallet health status before using it

        //TODO:Understand what id to use for uniquely identifying proper record
        val existingCredentialExchangeRecord = getCredentialExchangeRecordByThread(credentialExchangeRecord.thread)


        val value = IndySerializationUtils.jsonProcessor.encodeToString(credentialExchangeRecord)

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
            )

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

    override suspend fun getCredentialExchangeRecordByThread(thread: Thread): CredentialExchangeRecord? {

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
                )
            IndySerializationUtils.jsonProcessor.decodeFromString<CredentialExchangeRecord>(
                JsonUtils.extractValue(
                    retrievedValue
                )
            )
        } catch (w: WalletItemNotFoundException) {
            null //this will be the case for ios
        } catch (e: Exception) { //TODO: unify this check with the explicit exception as above. For this we will need to implement exceptions on common level for JVM and android impementations
//this will be the case for Android and JVM. To be unified
            if (e.message!!.contains("WalletItemNotFoundException"))
                null
            else
                throw e
        }

    }

    override fun extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo: CredentialRequestInfo): Data {

        //TODO: check if this type cast is needed here
        val credentialRequestJson =
            IndySerializationUtils.jsonProcessor.encodeToString(credentialRequestInfo.credentialRequest as IndyCredentialRequest)
        println("extractCredentialRequestDataFromCredentialInfo: credentialRequestJson = $credentialRequestJson")


        return Data(base64 = Base64.plainStringToBase64String(credentialRequestJson))

    }


    override suspend fun receiveCredential(
        credential: Credential,
        credentialRequestInfo: CredentialRequestInfo,
        credentialDefinition: CredentialDefinition,
        revocationRegistryDefinition: RevocationRegistryDefinition?
    ): String {

        val credentialJson =
            IndySerializationUtils.jsonProcessor.encodeToString(PolymorphicSerializer(Credential::class), credential)
        val credentialRequestMetadataJson =
            IndySerializationUtils.jsonProcessor.encodeToString(
                PolymorphicSerializer(CredentialRequestMetadata::class),
                credentialRequestInfo.credentialRequestMetadata
            )
        val credDefJson = IndySerializationUtils.jsonProcessor.encodeToString(
            PolymorphicSerializer(CredentialDefinition::class),
            credentialDefinition
        )
        val revRegDefJson =
            if (revocationRegistryDefinition == null)
                null
            else
                IndySerializationUtils.jsonProcessor.encodeToString(
                    PolymorphicSerializer(RevocationRegistryDefinition::class),
                    revocationRegistryDefinition
                )

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
        )

    }

    override suspend fun createRevocationState(
        revocationRegistryDefinition: RevocationRegistryDefinition,
        revocationRegistryEntry: RevocationRegistryEntry,
        credentialRevocationId: String,
        timestamp: Long
    ): RevocationState {

        val indyRevocationRegistryDefinition = revocationRegistryDefinition as IndyRevocationRegistryDefinition

        val tailsReaderHandle = TailsHelper.getTailsReaderHandler(tailsPath)

        val revRegDefJson = IndySerializationUtils.jsonProcessor.encodeToString(revocationRegistryDefinition)

        val revRegDeltaJson = IndySerializationUtils.jsonProcessor.encodeToString(revocationRegistryEntry)


        val revStateJson = Anoncreds.createRevocationState(
            tailsReaderHandle,
            revRegDefJson,
            revRegDeltaJson,
            timestamp,
            credentialRevocationId
        )

        val revocationState = IndySerializationUtils.jsonProcessor.decodeFromString<RevocationState>(revStateJson)
        revocationState.revocationRegistryIdRaw = indyRevocationRegistryDefinition.id

        return revocationState
    }

    override fun buildCredentialObjectFromRawData(data: Data): Credential {

        val indyCredentialJson = Base64.base64StringToPlainString(data.base64)

        val indyCredential =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredential>(indyCredentialJson)

        return indyCredential

    }

    override fun buildCredentialOfferObjectFromRawData(data: Data): CredentialOffer {

        val jsonCredentialOffer = Base64.base64StringToPlainString(data.base64)


        println("jsonCredentialOffer = $jsonCredentialOffer")

        val indyCredentialOffer =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyCredentialOffer>(
                jsonCredentialOffer
            )

        return indyCredentialOffer

    }

    override suspend fun removeCredentialExchangeRecordByThread(thread: Thread) {

        WalletRecord.delete(
            walletHolder.getWallet() as Wallet,
            WalletRecordType.CredentialExchangeRecord.name,
            thread.thid
        )
    }

    override fun buildPresentationRequestObjectFromRawData(data: Data): PresentationRequest {
        val indyPresentationRequestJson = Base64.base64StringToPlainString(data.base64)

        println("Received JSON PresentationRequest: $indyPresentationRequestJson")

        val indyPresentationReuqest =
            IndySerializationUtils.jsonProcessor.decodeFromString<IndyPresentationRequest>(indyPresentationRequestJson)

        return indyPresentationReuqest
    }

    override suspend fun createPresentation(
        presentationRequest: PresentationRequest,
        ledgerConnector: LedgerConnector,
        /* TODO: add extra query parameter
        extraQuery: Map<String, Map<String, Any>>?*/
    ): Presentation {

        val indyPresentationRequest = presentationRequest as IndyPresentationRequest

        val proofRequestJson = IndySerializationUtils.jsonProcessor.encodeToString(presentationRequest)

        println("In createPresentation function: proofRequestJson = $proofRequestJson")

        //TODO: deal with extra query. Understand what it is and how to use it. See cordentity
        val extraQueryJson = null

        val searchObj = CredentialsSearchForProofReq()
        searchObj.open(walletHolder.getWallet() as Wallet, proofRequestJson, extraQueryJson)


        val allSchemaIds = mutableListOf<IndySchemaId>()
        val allCredentialDefinitionIds = mutableListOf<IndyCredentialDefinitionId>()
        val allRevStates = mutableListOf<RevocationState>()

        //TODO: remove copypaste code from requestedAttributes and requestedPredicates
        val requestedAttributes = indyPresentationRequest.requestedAttributes.keys.associate { key ->

            val credentialJson = searchObj.fetchNextCredentials(key, 1)

            println("Retrieved for key = $key  -> credentialJson: $credentialJson")

            val credentialForTheRequest =
                IndySerializationUtils.jsonProcessor.decodeFromString<List<IndyCredentialForTheRequest>>(credentialJson)
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
            val credentialJson = searchObj.fetchNextCredentials(key, 1)

            println("Retrieved for key = $key  -> credentialJson: $credentialJson")

            val credentialForTheRequest =
                IndySerializationUtils.jsonProcessor.decodeFromString<List<IndyCredentialForTheRequest>>(credentialJson)
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

        searchObj.closeSearch()

        val requestedCredentials = RequestedCredentials(requestedAttributes, requestedPredicates, mapOf())
        val requestedCredentialsJson = IndySerializationUtils.jsonProcessor.encodeToString(requestedCredentials)

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

        val usedSchemasJson = IndySerializationUtils.jsonProcessor.encodeToString(usedSchemas)
        val usedCredentialDefsJson = IndySerializationUtils.jsonProcessor.encodeToString(usedCredentialDefs)
        val usedRevStatesJson = IndySerializationUtils.jsonProcessor.encodeToString(usedRevocationStates)


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
        )

        println("Indy proof created: $proverProofJson")

        val presentation = IndySerializationUtils.jsonProcessor.decodeFromString<IndyPresentation>(proverProofJson)

        return presentation
    }

    private suspend fun provideRevocationState(
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

    override fun extractPresentationDataFromPresentation(presentation: Presentation): Data {

        //TODO: check if this type cast is needed here
        val presentationJson =
            IndySerializationUtils.jsonProcessor.encodeToString(presentation as IndyPresentation)
        println("extractPresentationDataFromPresentation: presentationJson = $presentationJson")


        return Data(base64 = Base64.plainStringToBase64String(presentationJson))

    }


}

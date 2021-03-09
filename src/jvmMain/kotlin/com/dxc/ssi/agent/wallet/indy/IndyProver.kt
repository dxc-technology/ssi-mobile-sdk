package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.Prover
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.didcomm.model.issue.container.Data
import com.dxc.ssi.agent.didcomm.model.issue.data.*
import com.dxc.ssi.agent.didcomm.model.revokation.data.RevocationRegistryDefinition
import com.dxc.ssi.agent.model.CredentialExchangeRecord
import com.dxc.ssi.agent.utils.JsonUtils.extractValue
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils.jsonProcessor
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import com.dxc.ssi.agent.wallet.indy.model.issue.*
import com.fasterxml.jackson.databind.util.ClassUtil.getRootCause
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.hyperledger.indy.sdk.anoncreds.Anoncreds
import org.hyperledger.indy.sdk.anoncreds.DuplicateMasterSecretNameException
import org.hyperledger.indy.sdk.non_secrets.WalletRecord
import org.hyperledger.indy.sdk.wallet.Wallet
import org.hyperledger.indy.sdk.wallet.WalletItemNotFoundException
import java.util.*
import java.util.concurrent.ExecutionException

actual class IndyProver actual constructor(private val walletHolder: WalletHolder) : Prover {

    private var masterSecretId: String? = null

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

        return Anoncreds.proverStoreCredential(
            walletHolder.getWallet() as Wallet,
            null,
            credentialRequestMetadataJson,
            credentialJson,
            credDefJson,
            revRegDefJson
        ).get()

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

}

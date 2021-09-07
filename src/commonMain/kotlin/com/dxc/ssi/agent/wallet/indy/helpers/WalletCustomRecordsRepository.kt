package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.exceptions.indy.WalletItemNotFoundException
import com.dxc.ssi.agent.model.WalletStorable
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.libindy.WalletRecord
import com.dxc.ssi.agent.wallet.indy.libindy.WalletSearch
import com.dxc.ssi.agent.wallet.indy.model.CommonWalletRecord
import com.dxc.ssi.agent.wallet.indy.model.RetrievedWalletRecords
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import com.dxc.utils.Base64
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
object WalletCustomRecordsRepository {

    var logger: Kermit = Kermit(LogcatLogger())
    suspend inline fun <reified T : WalletStorable> upsertWalletRecord(wallet: Wallet, walletStorable: T) {

        val existingWalletRecord: T? = getWalletRecordById(wallet, walletStorable.id)

        val value = Base64.plainStringToBase64String(
            IndySerializationUtils.jsonProcessor.encodeToString(walletStorable)
        )

        val tagsJson = walletStorable.generateTagsJson()

        if (existingWalletRecord == null) {

            WalletRecord.add(
                wallet,
                WalletStorable.getWalletRecordType(T::class).name,
                walletStorable.id,
                value,
                tagsJson
            )

        } else {

            WalletRecord.updateValue(
                wallet,
                WalletStorable.getWalletRecordType(T::class).name,
                walletStorable.id,
                value
            )
            WalletRecord.updateTags(
                wallet,
                WalletStorable.getWalletRecordType(T::class).name,
                walletStorable.id,
                tagsJson
            )

        }


    }

    suspend inline fun <reified T : WalletStorable> getWalletRecordById(wallet: Wallet, id: String): T? {
        //TODO: use some serializable data structure
        val options = "{\"retrieveType\" : true}"

        //TODO: find out better solution of looking up for connection
        return try {
            val retrievedWalletRecord =
                WalletRecord.get(wallet, WalletStorable.getWalletRecordType(T::class).name, id, options)
            val walletRecord =
                IndySerializationUtils.jsonProcessor.decodeFromString<CommonWalletRecord>(retrievedWalletRecord)

            IndySerializationUtils.jsonProcessor.decodeFromString<T>(
                Base64.base64StringToPlainString(walletRecord.value)
            )

        } catch (e: WalletItemNotFoundException) {
            null
        }

    }

    suspend inline fun <reified T : WalletStorable> getWalletRecordsByQuery(wallet: Wallet, query: String): Set<T> {

        val retrievedWalletRecords = internalQueryWalletRecords(
            wallet,
            WalletStorable.getWalletRecordType(T::class),
            query
        )

        if (retrievedWalletRecords.totalCount == null || retrievedWalletRecords.totalCount == 0)
            return emptySet()

        return retrievedWalletRecords.records!!
            .map {
                logger.d { it.value }
                Base64.base64StringToPlainString(it.value)
            }.map<String, T> { IndySerializationUtils.jsonProcessor.decodeFromString(it) }
            .toSet()

    }


    suspend fun internalQueryWalletRecords(
        wallet: Wallet,
        walletRecordType: WalletRecordType,
        query: String
    ): RetrievedWalletRecords {
        logger.d { "Searching connections using query: $query" }

        val options = "{\"retrieveType\" : true, \"retrieveTotalCount\" : true}"

        val search = WalletSearch()

        search.open(wallet, walletRecordType.name, query, options)
        //TODO: make proper fetch in batches instead of just fetching 200 records
        val foundRecordsJson = search.searchFetchNextRecords(wallet, 200)
        search.closeSearch()

        logger.d { "Fetched connections json = $foundRecordsJson" }

        return Json.decodeFromString(foundRecordsJson)

    }

}
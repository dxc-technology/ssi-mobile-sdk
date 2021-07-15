package com.dxc.ssi.agent.wallet.indy.helpers

import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.libindy.WalletSearch
import com.dxc.ssi.agent.wallet.indy.model.RetrievedWalletRecords
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object WalletQueryHelper {

    suspend fun queryWalletRecords(
        wallet: Wallet,
        walletRecordType: WalletRecordType,
        query: String
    ): RetrievedWalletRecords {
        println("Searching connections using query: $query")

        val options = "{\"retrieveType\" : true, \"retrieveTotalCount\" : true}"

        val search = WalletSearch()

        search.open(wallet, walletRecordType.name, query, options)
        //TODO: make proper fetch in batches instead of just fetching 200 records
        val foundRecordsJson = search.searchFetchNextRecords(wallet, 200)
        search.closeSearch()

        println("Fetched connections json = $foundRecordsJson")

        return Json.decodeFromString(foundRecordsJson)

    }
}
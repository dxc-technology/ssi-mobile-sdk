package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import kotlin.reflect.KClass

interface ExchangeRecord {
    val thread: Thread
    fun generateTagsJson(): String

    companion object {
        fun <T : ExchangeRecord> getWalletRecordType(clazz: KClass<T>): WalletRecordType =
            when (clazz) {
                CredentialExchangeRecord::class -> WalletRecordType.CredentialExchangeRecord
                PresentationExchangeRecord::class -> WalletRecordType.PresentationExchangeRecord
                else -> throw IllegalArgumentException("Not supported class passed")

            }

        fun <T : ExchangeRecord> getWalletRecordStateTag(clazz: KClass<T>): WalletRecordTag =
            when (clazz) {
                CredentialExchangeRecord::class -> WalletRecordTag.CredentialExchangeRecordState
                PresentationExchangeRecord::class -> WalletRecordTag.PresentationExchangeRecordState
                else -> throw IllegalArgumentException("Not supported class passed")

            }

    }

}
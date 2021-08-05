package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import kotlin.reflect.KClass

interface WalletStorable {
    val id: String
    fun generateTagsJson(): String

    companion object {
        fun <T : WalletStorable> getWalletRecordType(clazz: KClass<T>): WalletRecordType =
            when (clazz) {
                CredentialExchangeRecord::class -> WalletRecordType.CredentialExchangeRecord
                PresentationExchangeRecord::class -> WalletRecordType.PresentationExchangeRecord
                PeerConnectionRecord::class -> WalletRecordType.ConnectionRecord
                else -> throw IllegalArgumentException("Not supported class passed")

            }

    }

}
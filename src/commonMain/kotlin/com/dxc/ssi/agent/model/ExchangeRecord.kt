package com.dxc.ssi.agent.model

import com.dxc.ssi.agent.didcomm.model.common.Thread
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import kotlin.reflect.KClass

interface ExchangeRecord: WalletStorable {
    val thread: Thread
    override val id: String
        get() = thread.thid

    companion object {

        fun <T : ExchangeRecord> getWalletRecordStateTag(clazz: KClass<T>): WalletRecordTag =
            when (clazz) {
                CredentialExchangeRecord::class -> WalletRecordTag.CredentialExchangeRecordState
                PresentationExchangeRecord::class -> WalletRecordTag.PresentationExchangeRecordState
                else -> throw IllegalArgumentException("Not supported class passed")

            }

    }

}
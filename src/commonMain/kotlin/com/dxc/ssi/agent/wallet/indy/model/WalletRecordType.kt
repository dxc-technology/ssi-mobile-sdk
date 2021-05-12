package com.dxc.ssi.agent.wallet.indy.model

import kotlinx.serialization.Serializable

@Serializable
enum class WalletRecordType {
    ConnectionRecord,
    CredentialExchangeRecord
}
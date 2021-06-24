package com.dxc.ssi.agent.exceptions.indy

import java.util.concurrent.ExecutionException

class IndyJvmToCommonExceptionConverter<T> {

    fun convertException(block: () -> T): T =

        try {
            block.invoke()
        } catch (e: ExecutionException) {
            throw when (e.cause) {
                is org.hyperledger.indy.sdk.wallet.WalletItemNotFoundException -> WalletItemNotFoundException()
                is org.hyperledger.indy.sdk.anoncreds.DuplicateMasterSecretNameException -> DuplicateMasterSecretNameException()
                else -> e
            }

        }

}


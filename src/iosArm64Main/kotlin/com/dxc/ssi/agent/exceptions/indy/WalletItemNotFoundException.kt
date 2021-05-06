package com.dxc.ssi.agent.exceptions.indy

actual class WalletItemNotFoundException() : IndyException(
    message = "No value with the specified key exists in the wallet from which it was requested.",
    sdkErrorCode = ErrorCode.WalletItemNotFound.value()
)
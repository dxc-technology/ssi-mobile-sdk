package com.dxc.ssi.agent.exceptions.indy

actual class InvalidParameterException actual constructor(sdkErrorCode: Int) : IndyException(
    //TODO: add logic for determining parameter id
    message = "Invalid parameter exception",
    sdkErrorCode = ErrorCode.WalletItemNotFound.value()
)
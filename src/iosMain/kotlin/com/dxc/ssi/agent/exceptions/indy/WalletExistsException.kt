package com.dxc.ssi.agent.exceptions.indy

actual class WalletExistsException actual constructor() : IndyException(
    message = "A wallet with the specified name already exists.",
    sdkErrorCode = ErrorCode.WalletItemNotFound.value()
)
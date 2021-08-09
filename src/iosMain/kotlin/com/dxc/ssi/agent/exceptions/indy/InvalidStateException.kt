package com.dxc.ssi.agent.exceptions.indy

actual class InvalidStateException actual constructor() : IndyException(
    message = "Probably an issue with the merkle tree",
    sdkErrorCode = ErrorCode.CommonInvalidState.value()
)
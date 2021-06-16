package com.dxc.ssi.agent.exceptions.indy

actual class DuplicateMasterSecretNameException actual constructor() : IndyException(
    message = "The master secret with the specified id already exists.",
    sdkErrorCode = ErrorCode.AnoncredsMasterSecretDuplicateNameError.value()
)
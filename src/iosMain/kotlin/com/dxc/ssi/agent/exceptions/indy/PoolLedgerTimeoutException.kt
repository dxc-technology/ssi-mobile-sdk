package com.dxc.ssi.agent.exceptions.indy

actual class PoolLedgerTimeoutException actual constructor() : IndyException(
    message = "Pool Ledger is not available",
    sdkErrorCode = ErrorCode.PoolLedgerTimeout.value()
)
package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.libindy.Pool

actual object PoolHelper {
    actual suspend fun openOrCreateFromFilename(filename: String): Pool {
        TODO("Not yet implemented")
    }

    actual suspend fun openOrCreateFromIp(
        ipAddress: String,
        dir: String
    ): Pool {
        TODO("Not yet implemented")
    }
}
package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.libindy.Pool

expect object PoolHelper {
    suspend fun openOrCreateFromFilename(filename: String): Pool
    suspend fun openOrCreateFromIp(ipAddress: String, dir: String): Pool
}
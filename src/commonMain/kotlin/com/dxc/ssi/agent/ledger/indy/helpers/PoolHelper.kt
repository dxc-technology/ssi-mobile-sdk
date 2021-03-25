package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.libindy.Pool

expect object PoolHelper {
    fun openOrCreateFromFilename(filename: String): Pool
}
package com.dxc.ssi.agent.wallet.indy.libindy

import kotlin.native.concurrent.AtomicInt

class Api {
    companion object {
        val atomicInteger: AtomicInt = AtomicInt(1)
    }
}
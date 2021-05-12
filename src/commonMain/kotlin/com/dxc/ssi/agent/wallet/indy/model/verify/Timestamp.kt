package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.utils.System

object Timestamp {
    fun now() = ( System.currentTimeMillis() / 1000)

}
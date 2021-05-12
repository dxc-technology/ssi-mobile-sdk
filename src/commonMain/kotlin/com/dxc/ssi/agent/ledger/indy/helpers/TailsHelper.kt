package com.dxc.ssi.agent.ledger.indy.helpers

//TODO: move logic of this class to common level
expect object TailsHelper {
    fun getTailsReaderHandler(tailsPath: String): Int
}
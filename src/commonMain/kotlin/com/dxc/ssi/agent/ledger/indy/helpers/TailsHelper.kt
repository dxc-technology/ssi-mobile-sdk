package com.dxc.ssi.agent.ledger.indy.helpers

expect object TailsHelper {
    fun getTailsReaderHandler(tailsPath: String): Int
}
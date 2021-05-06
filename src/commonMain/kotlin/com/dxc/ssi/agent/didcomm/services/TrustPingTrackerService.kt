package com.dxc.ssi.agent.didcomm.services

import co.touchlab.stately.collections.IsoMutableMap
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.actions.didexchange.impl.AbortConnection
import com.dxc.ssi.agent.model.Connection
import com.dxc.utils.Sleeper
import com.dxc.utils.System

//TODO: rewrite this class to be stateless and take infor from wallet. This is not needed for mobile library, but will be needed for scalable server-side library
class TrustPingTrackerService(
    val walletConnector: WalletConnector,
    val connectionInitiatorController: ConnectionInitiatorController
) {

    private var isShutdown = false

    private val maxTimeoutForTrustPingResponseMs = 20000
    private val sentPingsMap = IsoMutableMap<String/*ConnectionId*/, Long /*Timestamp when ping was sent*/>()

    suspend fun track() {
        println("Started listener")

        while (!isShutdown) {
            println("Checking trust pings states")
            val deadConnections = getDeadConnections()

            //TODO:actually abort dead connection shere
            abortDeadConnections(deadConnections)

            Sleeper().sleep(10000)
            println("Done checking trust pings states")
        }
    }

    private fun getDeadConnections(): Set<String> {

        val currentTimestamp = System.currentTimeMillis()
        return sentPingsMap.keys.filter { key -> currentTimestamp - sentPingsMap[key]!! > maxTimeoutForTrustPingResponseMs }
            .toSet()

    }

    private suspend fun abortDeadConnections(deadConnections: Set<String>) {
        deadConnections.forEach { connectionId ->
            AbortConnection(
                walletConnector,
                connectionInitiatorController,
                connectionId
            ).perform()
            sentPingsMap.remove(connectionId)
        }

    }


    fun trustPingSentOverConnectionEvent(connection: Connection) {
        println("TrustPing sent for connectionId = ${connection.id}")
        sentPingsMap[connection.id] = System.currentTimeMillis()
    }

    fun trustPingResponseReceivedEvent(connection: Connection) {
        println("TrustPing received for connectionId = ${connection.id}")
        sentPingsMap.remove(connection.id)
    }


    fun shutdown() {
        isShutdown = true
    }


}
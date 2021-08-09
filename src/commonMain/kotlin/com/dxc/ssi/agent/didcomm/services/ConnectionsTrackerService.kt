package com.dxc.ssi.agent.didcomm.services

import co.touchlab.stately.collections.IsoMutableMap
import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.api.Callbacks
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity
import com.dxc.ssi.agent.model.ConnectionTransportState
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.utils.System
import kotlinx.coroutines.*

//TODO: rewrite this class to be stateless and take infor from wallet. This is not needed for mobile library, but will be needed for scalable server-side library
class ConnectionsTrackerService(
    val walletConnector: WalletConnector,
    val callbacks: Callbacks,
    val processors: Processors
) {
    private val logger: Kermit = Kermit(LogcatLogger())

    private var job = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Default + job)

    private var isShutdown
        get() = isoIsShutdown.access { it.obj }!!
        set(value) {
            isoIsShutdown.access { it.obj = value }
        }

    private val isoIsShutdown = IsolateState { ObjectHolder(false) }

    private val maxTimeoutForTrustPingResponseMs = 120_000L
    private val sendTrustPingIntervalMs = 60_000L
    private val sentPingsMap = IsoMutableMap<String/*ConnectionId*/, Long /*Timestamp when ping was sent*/>()

    suspend fun start() {
        logger.log(Severity.Debug,"",null) { "Started listener" }

        serviceScope.launch {
            trackTrustPingStatuses()
        }

        serviceScope.launch {
            generateKeepAliveTrustPings()
        }
    }

    private suspend fun generateKeepAliveTrustPings() {
        while (!isShutdown) {
            logger.log(Severity.Debug,"",null) { "Trust Ping Generator woken up" }

            //TODO: also introduce somewhere removal of outdated IN Progress connection records
            walletConnector.walletHolder.getConnections(PeerConnectionState.COMPLETED).filter { it.keepTransportAlive }
                .forEach {
                    serviceScope.launch {
                        logger.log(Severity.Debug,"",null) { "generating trust ping for connection $it" }
                        processors.trustPingProcessor!!.sendTrustPingOverConnection(it)
                    }
                }
            delay(sendTrustPingIntervalMs)
            logger.log(Severity.Debug,"",null) { "Sent trust pings for connections" }
        }

    }


    private suspend fun trackTrustPingStatuses() {
        while (!isShutdown) {
            logger.log(Severity.Debug,"",null) { "Checking trust pings states" }

            getNotResponsiveConnections().forEach {
                sentPingsMap.remove(it.id)
                callbacks.trustPingController?.onTrustPingResponseDidNotReceived(it)

            }

            delay(maxTimeoutForTrustPingResponseMs)
            logger.log(Severity.Debug,"",null) { "Done checking trust pings states" }

        }

    }

    private suspend fun getNotResponsiveConnections(): Set<PeerConnection> {

        val currentTimestamp = System.currentTimeMillis()
        return sentPingsMap.keys.filter { key -> currentTimestamp - sentPingsMap[key]!! > maxTimeoutForTrustPingResponseMs }
            .mapNotNull { walletConnector.walletHolder.getConnectionRecordById(it) }
            .toSet()

    }

    fun trustPingSentOverConnectionEvent(connection: PeerConnection) {
        logger.log(Severity.Debug,"",null) { "TrustPing sent for connectionId = ${connection.id}" }
        sentPingsMap[connection.id] = System.currentTimeMillis()
    }

    fun trustPingResponseReceivedEvent(connection: PeerConnection) {
        logger.log(Severity.Debug,"",null) { "TrustPing received for connectionId = ${connection.id}" }
        sentPingsMap.remove(connection.id)
    }


    fun shutdown() {
        isShutdown = true
    }

    suspend fun keepConnectionAlive(connection: PeerConnection, keepConnectionAlive: Boolean) {
        val existingConnection = walletConnector.walletHolder.getConnectionRecordById(connection.id)

        existingConnection?.let {
            walletConnector.walletHolder.storeConnectionRecord(it.copy(keepTransportAlive = keepConnectionAlive))
        }


    }

    fun reconnect(connection: PeerConnection, keepConnectionAlive: Boolean) {

        serviceScope.launch {
            logger.log(Severity.Debug,"",null) { "generating trust ping for connection $connection" }
            processors.trustPingProcessor!!.sendTrustPingOverConnection(connection)
            val actualConnection = walletConnector.walletHolder.getConnectionRecordById(connection.id)!!
            callbacks.statefulConnectionController?.onReconnected(actualConnection)
        }

    }

    suspend fun setConnectionTransportState(
        connection: PeerConnection,
        connectionTransportState: ConnectionTransportState
    ) {
        walletConnector.walletHolder.getConnectionRecordById(connection.id)?.let {
            if (connection.transportState != it.transportState) {
                val updatedConnection = it.copy(transportState = connectionTransportState)
                walletConnector.walletHolder.storeConnectionRecord(updatedConnection)

                if (connectionTransportState == ConnectionTransportState.DISCONNECTED)
                    callbacks.statefulConnectionController?.onDisconnected(updatedConnection)
            }
        }

    }


}
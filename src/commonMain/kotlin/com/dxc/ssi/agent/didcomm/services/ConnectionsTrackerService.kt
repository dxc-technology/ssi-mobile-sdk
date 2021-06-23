package com.dxc.ssi.agent.didcomm.services

import co.touchlab.stately.collections.IsoMutableMap
import com.benasher44.uuid.uuid4
import com.dxc.ssi.agent.api.callbacks.didexchange.ConnectionInitiatorController
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.constants.DidCommProblemCodes
import com.dxc.ssi.agent.didcomm.constants.toProblemReportDescription
import com.dxc.ssi.agent.didcomm.model.problem.ProblemReport
import com.dxc.ssi.agent.didcomm.processor.Processors
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.utils.System
import kotlinx.coroutines.*

//TODO: rewrite this class to be stateless and take infor from wallet. This is not needed for mobile library, but will be needed for scalable server-side library
class ConnectionsTrackerService(
    val walletConnector: WalletConnector,
    val connectionInitiatorController: ConnectionInitiatorController,
    val processors: Processors
) {

    private var job = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Default + job)

    private var isShutdown = false

    private val maxTimeoutForTrustPingResponseMs = 120_000L
    private val sendTrustPingIntervalMs = 60_000L
    private val sentPingsMap = IsoMutableMap<String/*ConnectionId*/, Long /*Timestamp when ping was sent*/>()

    suspend fun start() {
        println("Started listener")
        serviceScope.launch {
            trackTrustPingStatuses()
        }

        serviceScope.launch {
            generateKeepAliveTrustPings()
        }
    }

    private suspend fun generateKeepAliveTrustPings() {
        while (!isShutdown) {
            println("Trust Ping Generator woken up")

            //TODO: also introduce somewhere removal of outdated IN Progress connection records
            walletConnector.walletHolder.getConnections(PeerConnectionState.COMPLETED).forEach {
                serviceScope.launch {
                    println("generating trust ping for connection $it")
                    processors.trustPingProcessor!!.sendTrustPingOverConnection(it)
                }
            }
            delay(sendTrustPingIntervalMs)
            println("Sent trust pings for connections")
        }

    }


    private suspend fun trackTrustPingStatuses() {
        while (!isShutdown) {
            println("Checking trust pings states")
            getDeadConnections().forEach {
                sentPingsMap.remove(it.id)
                val problemReport = ProblemReport(
                    id = uuid4().toString(),
                    description = DidCommProblemCodes.PEER_HAVE_NOT_REPLIED_ON_TRUST_PING.toProblemReportDescription()
                )
                processors.abandonConnectionProcessor!!.abandonConnection(it, false, problemReport)

            }

            delay(maxTimeoutForTrustPingResponseMs)
            println("Done checking trust pings states")
        }

    }

    private suspend fun getDeadConnections(): Set<PeerConnection> {

        val currentTimestamp = System.currentTimeMillis()
        return sentPingsMap.keys.filter { key -> currentTimestamp - sentPingsMap[key]!! > maxTimeoutForTrustPingResponseMs }
            .mapNotNull { walletConnector.walletHolder.getConnectionRecordById(it) }
            .toSet()

    }

    fun trustPingSentOverConnectionEvent(connection: PeerConnection) {
        println("TrustPing sent for connectionId = ${connection.id}")
        sentPingsMap[connection.id] = System.currentTimeMillis()
    }

    fun trustPingResponseReceivedEvent(connection: PeerConnection) {
        println("TrustPing received for connectionId = ${connection.id}")
        sentPingsMap.remove(connection.id)
    }


    fun shutdown() {
        isShutdown = true
    }


}
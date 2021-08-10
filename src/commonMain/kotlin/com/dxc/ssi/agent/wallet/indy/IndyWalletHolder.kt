package com.dxc.ssi.agent.wallet.indy

import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.*
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import com.dxc.ssi.agent.wallet.indy.helpers.WalletCustomRecordsRepository
import com.dxc.ssi.agent.wallet.indy.libindy.Crypto
import com.dxc.ssi.agent.wallet.indy.libindy.Did
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import com.dxc.ssi.agent.kermit.Severity

open class IndyWalletHolder(
    private val walletName: String,
    private val walletPassword: String,
    private val didConfig: DidConfig
) : WalletHolder {
    var logger: Kermit = Kermit(LogcatLogger())
    //TODO: think how to avoid optionals here
    private var isoDid = IsolateState { ObjectHolder<String?>() }
    private var isoVerkey = IsolateState { ObjectHolder<String?>() }

    private var isoWallet = IsolateState { ObjectHolder<Wallet>() }

    private var job = Job()
    private val walletHolderScope = CoroutineScope(Dispatchers.Default + job)

    override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    override fun getIdentityDetails(): IdentityDetails {
        return IdentityDetails(isoDid.access { it.obj }!!, isoVerkey.access { it.obj }!!, null, null)
    }

    override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    override suspend fun storeConnectionRecord(connection: PeerConnection) {
        val wallet = isoWallet.access { it.obj }!!
        WalletCustomRecordsRepository.upsertWalletRecord(wallet, PeerConnectionRecord(connection))
    }

    override suspend fun getConnectionRecordById(connectionId: String): PeerConnection? {

        val wallet = isoWallet.access { it.obj }!!
        val peerConnectionRecord: PeerConnectionRecord? =
            WalletCustomRecordsRepository.getWalletRecordById(wallet, connectionId)

        return peerConnectionRecord?.peerConnection
    }

    override suspend fun findConnectionByVerKey(verKey: String): PeerConnection? {

        val query = "{\"${WalletRecordTag.ConnectionVerKey.name}\": \"${verKey}\"}"
        val wallet = isoWallet.access { it.obj }!!

        return WalletCustomRecordsRepository.getWalletRecordsByQuery<PeerConnectionRecord>(wallet, query)
            .map { it.peerConnection }.firstOrNull()
    }

    override suspend fun getConnections(connectionState: PeerConnectionState?): Set<PeerConnection> {

        val query = if (connectionState != null) {
            "{\"${WalletRecordTag.ConnectionState.name}\": \"${connectionState!!.name}\"}"
        } else {
            ""
        }

        val wallet = isoWallet.access { it.obj }!!

        return WalletCustomRecordsRepository.getWalletRecordsByQuery<PeerConnectionRecord>(wallet, query)
            .map { it.peerConnection }.toSet()

    }

    //TODO: remove this fun if it is not used
    override suspend fun isWalletHolderInitialized(): Boolean {
        return (isoWallet.access { it.obj } != null && isoDid.access { it.obj } != null && isoVerkey.access { it.obj } != null)
    }

    override fun getWallet(): Any {
        //TODO: see if it is possible to return common Wallet instead of Any
        val wallet = isoWallet.access { it.obj }
        return wallet as Any
    }

    //TODO: remove all unnecessary code and beautify this function
    override suspend fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        val byteArrayMessage = message.payload.toByteArray()
        val recipientVk = recipientKeys.joinToString(separator = "\",\"", prefix = "[\"", postfix = "\"]")
        //val recipientVk = recipientKeys.joinToString(separator = ",",prefix = "", postfix = "")
        logger.log(Severity.Debug,"",null) { "recipientKeys = $recipientVk" }

        val senderVk = if (useAnonCrypt) null else isoVerkey.access { it.obj }
        val wallet = isoWallet.access { it.obj }
        val byteArrayPackedMessage = Crypto.packMessage(wallet!!, recipientVk, senderVk, byteArrayMessage)

        val decodedString = String(byteArrayPackedMessage)

        logger.log(Severity.Debug,"",null) { "Decoded packed message = $decodedString" }

        return decodedString
    }

    //TODO: remove all unnecessary code and beautify this function
    override suspend fun unPackMessage(packedMessage: Message): Message {

        val byteArrayMessage = packedMessage.payload.toByteArray()

        val wallet = isoWallet.access { it.obj }
        val byteArrayUnpackedMessage = Crypto.unpackMessage(wallet!!, byteArrayMessage)

        val decodedString = String(byteArrayUnpackedMessage)

        logger.log(Severity.Debug,"",null) { "Decoded packed message = $decodedString" }

        return Message(decodedString)

    }

    override suspend fun openWalletOrFail() {

        val wallet = WalletHelper.openExisting(walletName, walletPassword)
        isoWallet.access { it.obj = wallet }

    }

    override suspend fun initializeDid() {
        val wallet = isoWallet.access { it.obj }
        val didWithMetaResult = Did.getDidWithMeta(wallet!!, didConfig.did!!)
        isoDid.access { it.obj = didWithMetaResult.did }
        isoVerkey.access { it.obj = didWithMetaResult.verkey }
    }

}
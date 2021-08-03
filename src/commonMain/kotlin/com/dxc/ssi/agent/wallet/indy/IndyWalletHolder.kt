package com.dxc.ssi.agent.wallet.indy

import co.touchlab.stately.isolate.IsolateState
import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.exceptions.indy.WalletItemNotFoundException
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.PeerConnection
import com.dxc.ssi.agent.model.PeerConnectionState
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.utils.ObjectHolder
import com.dxc.ssi.agent.utils.indy.IndySerializationUtils
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import com.dxc.ssi.agent.wallet.indy.helpers.WalletQueryHelper
import com.dxc.ssi.agent.wallet.indy.libindy.Crypto
import com.dxc.ssi.agent.wallet.indy.libindy.Did
import com.dxc.ssi.agent.wallet.indy.libindy.Wallet
import com.dxc.ssi.agent.wallet.indy.libindy.WalletRecord
import com.dxc.ssi.agent.wallet.indy.model.CommonWalletRecord
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordTag
import com.dxc.ssi.agent.wallet.indy.model.WalletRecordType
import com.dxc.utils.Base64
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class IndyWalletHolder(
    private val walletName: String,
    private val walletPassword: String,
    private val didConfig: DidConfig
) : WalletHolder {
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
        //TODO: check if we need to check wallet health status before using it
        val existingConnection = getConnectionRecordById(connection.id)
        val tagsJson =
            "{\"${WalletRecordTag.ConnectionVerKey.name}\": \"${connection.peerVerkey}\", \"${WalletRecordTag.ConnectionState.name}\": \"${connection.state.name}\"}"

        val value = Base64.plainStringToBase64String(Json.encodeToString(connection))
        if (existingConnection == null) {
            println("Storing connection $connection")
            val wallet = isoWallet.access { it.obj }
            WalletRecord.add(wallet!!, WalletRecordType.ConnectionRecord.name, connection.id, value, tagsJson)
        } else {
            println("Updating connection $connection")
            val wallet = isoWallet.access { it.obj }
            WalletRecord.updateValue(wallet!!, WalletRecordType.ConnectionRecord.name, connection.id, value)
            //TODO: check if there are cases when we really need to update tags
            WalletRecord.updateTags(wallet, WalletRecordType.ConnectionRecord.name, connection.id, tagsJson)
        }
    }

    override suspend fun getConnectionRecordById(connectionId: String): PeerConnection? {

        //TODO: use some serializable data structure
        val options = "{\"retrieveType\" : true}"
        /*
        *  retrieveType: (optional, false by default) Retrieve record type,
        * retrieveValue: (optional, true by default) Retrieve record value,
        * retrieveTags: (optional, true by default) Retrieve record tags }
        *
        * */


        //TODO: find out better solution of looking up for connection
        return try {
            val wallet = isoWallet.access { it.obj }
            val retrievedWalletRecord =
                WalletRecord.get(wallet!!, WalletRecordType.ConnectionRecord.name, connectionId, options)
            val walletRecord =
                IndySerializationUtils.jsonProcessor.decodeFromString<CommonWalletRecord>(retrievedWalletRecord)
            Json{ignoreUnknownKeys = true}.decodeFromString<PeerConnection>(Base64.base64StringToPlainString(walletRecord.value))
        } catch (e: WalletItemNotFoundException) {
            null
        }
    }

    override suspend fun findConnectionByVerKey(verKey: String): PeerConnection? {

        val query = "{\"${WalletRecordTag.ConnectionVerKey.name}\": \"${verKey}\"}"
        val wallet = isoWallet.access { it.obj }!!
        val retrievedWalletRecords =
            WalletQueryHelper.queryWalletRecords(wallet, WalletRecordType.ConnectionRecord, query)

        if (retrievedWalletRecords.totalCount == null || retrievedWalletRecords.totalCount == 0)
            return null

        //TODO: consider case when we received several records. Is it ok? What do do in this case? Need to make some robust solution instead of taking first one
        return retrievedWalletRecords.records!!
            .map {
                println(it.value)
                it.value
            }.map<String, PeerConnection> { Json.decodeFromString(it) }
            .firstOrNull()

    }

    override suspend fun getConnections(connectionState: PeerConnectionState?): Set<PeerConnection> {

        val query = if (connectionState != null) {
            "{\"${WalletRecordTag.ConnectionState.name}\": \"${connectionState!!.name}\"}"
        } else {
            ""
        }

        val wallet = isoWallet.access { it.obj }!!
        val retrievedWalletRecords =
            WalletQueryHelper.queryWalletRecords(wallet, WalletRecordType.ConnectionRecord, query)

        if (retrievedWalletRecords.totalCount == null || retrievedWalletRecords.totalCount == 0)
            return emptySet()

        return retrievedWalletRecords.records!!
            .map {
                println(it.value)
                it.value
            }.map<String, PeerConnection> { Json.decodeFromString(it) }.toSet()

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
        println("recipientKeys = $recipientVk")

        val senderVk = if (useAnonCrypt) null else isoVerkey.access { it.obj }
        val wallet = isoWallet.access { it.obj }
        val byteArrayPackedMessage = Crypto.packMessage(wallet!!, recipientVk, senderVk, byteArrayMessage)

        val decodedString = String(byteArrayPackedMessage)

        println("Decoded packed message = $decodedString")

        return decodedString
    }

    //TODO: remove all unnecessary code and beautify this function
    override suspend fun unPackMessage(packedMessage: Message): Message {

        val byteArrayMessage = packedMessage.payload.toByteArray()

        val wallet = isoWallet.access { it.obj }
        val byteArrayUnpackedMessage = Crypto.unpackMessage(wallet!!, byteArrayMessage)

        val decodedString = String(byteArrayUnpackedMessage)

        println("Decoded packed message = $decodedString")

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
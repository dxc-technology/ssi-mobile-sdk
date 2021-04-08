package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import com.dxc.ssi.agent.wallet.indy.libindy.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


open class IndyWalletHolder : WalletHolder {
    //TODO: think where do we need to store did
    //TODO: think how to avoid optionals here
    //understand what is the proper place for storing did
    private var did: String? = null
    private var verkey: String? = null

    //TODO: think if it is posible to make val lateinit or something like that
    private var wallet: Wallet? = null
    private val type = "ConnectionRecord"


    override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    override fun getIdentityDetails(): IdentityDetails {
        return IdentityDetails(did!!, verkey!!, null, null)
    }

    override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    override fun storeConnectionRecord(connection: Connection) {

        //TODO: check if we need to check wallet health status before using it

        val existingConnection = getConnectionRecordById(connection.id)

        if (existingConnection == null) {

            val value = connection.toJson()
            //TODO: think what tags do we need here
            val tagsJson:String? = null

            WalletRecord.add(wallet!!, type, connection.id, value, tagsJson)

        } else {

            //TODO: see if we also need to update tags

            val value = connection.toJson()
            WalletRecord.updateValue(wallet!!, type, connection.id, value)


        }


    }

    override fun getConnectionRecordById(connectionId: String): Connection? {

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
            val retrievedValue = WalletRecord.get(wallet!!, type, connectionId, options)
            Connection.fromJson(extractValue(retrievedValue))
        } catch (e: Exception) {
            //TODO: understand what ExecutionException in java implementation corresponds to in kotlin code
            //TODO: check how to compare exact exception class rather than message contains string
            if (e.message!!.contains("WalletItemNotFoundException") )
                null
            else
                throw e
        }


    }

    private fun extractValue(retrievedValue: String?): String {

        val group = Regex("value\":\"(.*})\",").find(retrievedValue!!)!!.groups[1]!!.value.replace("\\", "")
        println(group)

        return group
    }

    override fun openOrCreateWallet() {

        //TODO: think where to store name and password and how to pass it properly
        val walletName = "testWalletName"
        val walletPassword = "testWalletPassword"

        //TODO: remove this line in order to not clear wallet each time

        WalletHelper.createOrTrunc(walletName = walletName, walletPassword = walletPassword)
        wallet = WalletHelper.openOrCreate(walletName = walletName, walletPassword = walletPassword)

        //TODO: do not recreate did each time on wallet opening
        //TODO: alow to provide specific DID config

        val didConfigJson = Json.encodeToString(DidConfig())
        val didResult = Did.createAndStoreMyDid(wallet!!, didConfigJson)
        val didElem = didResult.getDid()
        val verkeyElem = didResult.getVerkey()

        did = didElem
        verkey = verkeyElem
    }

    //TODO: remove all unnecessary code and beautify this function
    override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        val byteArrayMessage = message.payload.toByteArray()   //Maybe Charset UTF8
        val recipientVk = recipientKeys.joinToString(separator = "\",\"", prefix = "[\"", postfix = "\"]")
        //val recipientVk = recipientKeys.joinToString(separator = ",",prefix = "", postfix = "")
        println("recipientKeys = $recipientVk")

        val senderVk = if (useAnonCrypt) null else verkey

        val byteArrayPackedMessage = Crypto.packMessage(wallet!!, recipientVk, senderVk, byteArrayMessage)

        val decodedString = String(byteArrayPackedMessage)

        println("Decoded packed message = $decodedString")

        return decodedString
    }

    //TODO: remove all unnecessary code and beautify this function
    override fun unPackMessage(packedMessage: Message): Message {

        val byteArrayMessage = packedMessage.payload.toByteArray()

        val byteArrayUnpackedMessage = Crypto.unpackMessage(wallet!!, byteArrayMessage)

        val decodedString = String(byteArrayUnpackedMessage)

        println("Decoded packed message = $decodedString")

        return Message(decodedString)

    }
}
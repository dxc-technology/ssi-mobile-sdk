package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message
import com.dxc.ssi.agent.utils.JsonUtils.extractValue
import com.dxc.ssi.agent.wallet.indy.helpers.WalletHelper
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.crypto.Crypto
import org.hyperledger.indy.sdk.did.Did
import org.hyperledger.indy.sdk.non_secrets.WalletRecord
import org.hyperledger.indy.sdk.wallet.Wallet
import org.hyperledger.indy.sdk.wallet.WalletItemNotFoundException
import java.util.concurrent.ExecutionException


actual open class IndyWalletHolder : WalletHolder {
    //TODO: think where do we need to store did
    //TODO: think how to avoid optionals here
    //understand what is the proper place for storing did
    private var did: String? = null
    private var verkey: String? = null

    //TODO: think if it is posible to make val lateinit or something like that
    private var wallet: Wallet? = null
    private val type = "ConnectionRecord"





    actual override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(): IdentityDetails {
        return IdentityDetails(did!!, verkey!!, null, null)
    }

    actual override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    actual override fun storeConnectionRecord(connection: Connection) {

        //TODO: check if we need to check wallet health status before using it

        val existingConnection = getConnectionRecordById(connection.id)

        if (existingConnection == null) {

            val value = connection.toJson()
            //TODO: think what tags do we need here
            val tagsJson = null

            WalletRecord.add(wallet, type, connection.id, value, tagsJson).get()

        } else {

            //TODO: see if we also need to update tags

            val value = connection.toJson()
            WalletRecord.updateValue(wallet, type, connection.id, value)


        }


    }

    actual override fun getConnectionRecordById(connectionId: String): Connection? {

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
            val retrievedValue = WalletRecord.get(wallet, type, connectionId, options).get()
            Connection.fromJson(extractValue(retrievedValue))
        } catch (e: ExecutionException) {
            if (e.cause is WalletItemNotFoundException)
                null
            else
                throw e
        }


    }

    actual override fun openOrCreateWallet() {

        //TODO: think where to store name and password and how to pass it properly
        val walletName = "testWalletName"
        val walletPassword = "testWalletPassword"

        //TODO: remove this line in order to not clear wallet each time
        WalletHelper.createOrTrunc(walletName = walletName, walletPassword = walletPassword)
        wallet = WalletHelper.openOrCreate(walletName = walletName, walletPassword = walletPassword)

        //TODO: do not recreate did each time on wallet opening
        //TODO: alow to provide specific DID config

        val didConfigJson = Json.encodeToString(DidConfig())
        val didResult = Did.createAndStoreMyDid(wallet, didConfigJson).get()
        did = didResult.did
        verkey = didResult.verkey
    }

    override fun getWallet(): Any {
        TODO("Not yet implemented")
    }

    //TODO: remove all unnecessary code and beautify this function
    actual override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        val byteArrayMessage = message.payload.toByteArray()
        val recipientVk = recipientKeys.joinToString(separator = "\",\"",prefix = "[\"", postfix = "\"]")
        //val recipientVk = recipientKeys.joinToString(separator = ",",prefix = "", postfix = "")
        println("recipientKeys = $recipientVk")

        val senderVk = if(useAnonCrypt)  null else verkey

        val byteArrayPackedMessage =  Crypto.packMessage(wallet, recipientVk, senderVk, byteArrayMessage).get()

        val decodedString = String(byteArrayPackedMessage)

        println("Decoded packed message = $decodedString")

        return decodedString
    }

    //TODO: remove all unnecessary code and beautify this function
    actual override fun unPackMessage(packedMessage: Message): Message {

        val byteArrayMessage = packedMessage.payload.toByteArray()

        val byteArrayUnpackedMessage =  Crypto.unpackMessage(wallet, byteArrayMessage).get()

        val decodedString = String(byteArrayUnpackedMessage)

        println("Decoded packed message = $decodedString")

        return Message(decodedString)

    }

    actual override  fun findConnectionByVerKey(verKey: String): Connection? {
        TODO("Not yet implemented")
    }
}
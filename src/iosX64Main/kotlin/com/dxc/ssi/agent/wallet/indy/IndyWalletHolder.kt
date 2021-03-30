package com.dxc.ssi.agent.wallet.indy

import com.dxc.ssi.agent.api.pluggable.wallet.WalletHolder
import com.dxc.ssi.agent.model.Connection
import com.dxc.ssi.agent.model.DidConfig
import com.dxc.ssi.agent.model.IdentityDetails
import com.dxc.ssi.agent.model.messages.Message
import helpers.WalletHelper

//import helpers.WalletHelper
//import utils.SerializationUtils


actual open class IndyWalletHolder: WalletHolder {
    actual override fun createSessionDid(identityRecord: IdentityDetails): String {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getIdentityDetails(did: String): IdentityDetails {
        TODO("Not yet implemented")
    }

    actual override fun getTailsPath(): String {
        TODO("Not yet implemented")
    }

    actual override fun storeConnectionRecord(connection: Connection) {
        TODO("Not yet implemented")
    }

    actual override fun getConnectionRecordById(connectionId: String): Connection? {
        TODO("Not yet implemented")
    }

    actual override fun openOrCreateWallet() {
        //TODO: think where to store name and password and how to pass it properly
        val walletName = "testWalletName"
        val walletPassword = "testWalletPassword"

//        //TODO: remove this line in order to not clear wallet each time
//        WalletHelper.createOrTrunc(walletName = walletName, walletPassword = walletPassword)
        var wallet = WalletHelper.openOrCreate(walletName = walletName, walletPassword = walletPassword)

        //TODO: do not recreate did each time on wallet opening
        //TODO: alow to provide specific DID config
        //val didResult = Did.createAndStoreMyDid(2, "{}") // SerializationUtils.anyToJSON(DidConfig())).get()
        //did = didResult.did
        //verkey = didResult.verkey
    }

    actual override fun packMessage(message: Message, recipientKeys: List<String>, useAnonCrypt: Boolean): String {
        TODO("Not yet implemented")
    }

    actual override fun unPackMessage(packedMessage: Message): Message {
        TODO("Not yet implemented")
    }

}
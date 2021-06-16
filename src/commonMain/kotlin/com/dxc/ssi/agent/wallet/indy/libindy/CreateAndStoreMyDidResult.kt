package com.dxc.ssi.agent.wallet.indy.libindy

//TODO: can we use only common implementation here?
expect class CreateAndStoreMyDidResult {
    fun getDid(): String
    fun getVerkey(): String
}

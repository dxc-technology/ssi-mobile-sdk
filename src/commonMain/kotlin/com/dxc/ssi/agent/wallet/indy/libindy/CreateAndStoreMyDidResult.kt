package com.dxc.ssi.agent.wallet.indy.libindy

expect class CreateAndStoreMyDidResult {
    fun getDid(): String
    fun getVerkey(): String
}

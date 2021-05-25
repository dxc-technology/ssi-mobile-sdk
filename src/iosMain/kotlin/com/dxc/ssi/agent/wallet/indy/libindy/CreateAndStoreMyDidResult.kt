package com.dxc.ssi.agent.wallet.indy.libindy

actual class CreateAndStoreMyDidResult(private var Did: String = "", private var VerKey: String = "") {
    actual fun getDid(): String {
        return Did
    }

    actual fun getVerkey(): String {
        return VerKey
    }
}
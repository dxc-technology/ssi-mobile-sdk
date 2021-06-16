package com.dxc.ssi.agent.config

//TODO: think if it is appropriate to move some configuration here
class Configuration() {
    companion object {
        /** TODO: see below
         * Currently this constant is located here, because its usage not clear,
         * When we implement tails helper for all platforms and move it to common module
         *  I think it will be required when we implement revocation functionality
         * */
        const val tailsPath: String = "/tmp/tails/"
        /**
         * This variable is just an id of secret in wallet, so it is not private information
         * For now there is no need to have it configurable unless we want to use different masterSecretId foк different credentials
         * For more information see: https://github.com/hyperledger-archives/indy-crypto/blob/master/libindy-crypto/docs/anoncreds-design.md
         * We can change it when there is specific use case when it is needed
         *
         */
        const val masterSecretId: String = "masterSecretId"
    }
}
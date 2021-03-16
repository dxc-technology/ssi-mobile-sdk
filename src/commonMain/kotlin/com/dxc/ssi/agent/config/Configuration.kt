package com.dxc.ssi.agent.config

//TODO: think if it is appropriate to move some configuration here
class Configuration() {
    companion object {
        //TODO: see where those configs should be located
        var genesisFilePath: String = "/home/ifedyanin/source/github/fedyiv/ssi-mobile-sdk-lumedic/files/docker_pool_transactions_genesis.txt"


        //TODO: see where those configs should be located
        val RETRY_TIMES: Int = 5
        val RETRY_DELAY_MS: Long = 5000

        //TODO:see what is meaning of this master secret id and decide whether it should be in configuration or it should be part of the state
        val masterSecretId: String = "masterSecretId"
    }
}
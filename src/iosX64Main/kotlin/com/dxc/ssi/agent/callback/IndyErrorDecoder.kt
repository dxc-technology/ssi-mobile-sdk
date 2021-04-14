package com.dxc.ssi.agent.callback

object IndyErrorDecoder {
    fun decodeErrorToException(errorCode: UInt): Exception {

        //TODO: implement error handling
        throw RuntimeException("Indy exception with error code $errorCode happened. To implement IndyException class")

    }
}
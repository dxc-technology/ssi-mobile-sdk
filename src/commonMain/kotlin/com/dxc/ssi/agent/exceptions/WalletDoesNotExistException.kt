package com.dxc.ssi.agent.exceptions

class WalletDoesNotExistException(override val message:String): RuntimeException(message) {
}
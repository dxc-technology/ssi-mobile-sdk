package com.dxc.ssi.agent.exceptions.wallet

class WalletDoesNotExistException(override val message:String): RuntimeException(message) {
}
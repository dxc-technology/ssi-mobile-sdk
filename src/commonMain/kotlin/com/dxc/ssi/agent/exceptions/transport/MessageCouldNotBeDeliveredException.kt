package com.dxc.ssi.agent.exceptions.transport

class MessageCouldNotBeDeliveredException(override val message: String) : RuntimeException(message)
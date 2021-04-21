package com.dxc.ssi.agent.transport

import kotlinx.coroutines.channels.Channel

class SocketListenerLoosingAdapter(
    //TODO: replace with CompletableDeferred?
    val socketOpenedChannel: Channel<Unit> = Channel(),
    val socketReceivedMessageChannel: Channel<String> = Channel(),
    val socketFailureChannel: Channel<Throwable> = Channel(),
    val socketClosedChannel: Channel<SocketClosureDetails> = Channel()
)
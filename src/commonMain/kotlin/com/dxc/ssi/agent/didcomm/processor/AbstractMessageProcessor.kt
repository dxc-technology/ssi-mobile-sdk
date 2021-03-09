package com.dxc.ssi.agent.didcomm.processor

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.states.State
import com.dxc.ssi.agent.didcomm.states.StateMachine
import com.dxc.ssi.agent.model.messages.MessageContext

abstract class AbstractMessageProcessor(
    protected val walletConnector: WalletConnector,
    protected val transport: Transport
) : MessageProcessor {

    abstract fun getStateMachine(): StateMachine
    abstract fun getCurrentState(): State

    override fun processMessage(messageContext: MessageContext) {
        // 1. Get current state from wallet for this connection
        println("Started processing message $messageContext")
        val currentState = getCurrentState()
        // 2. Pass message and current state to state machine
        ///val (nextState, action) = getStateMachine().getNextStateAndAction(currentState, messageContext)
        // 3. Get next state and action from state machine
      //  action.perform()
        // 4. Perform the required action/actions
        // 5. See if next state machine invocation is needed and repeat steps 1-5 if necessary
        // 6. Finish processing


    }
}
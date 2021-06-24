package com.dxc.ssi.agent.didcomm.states.didexchange

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.didcomm.states.State
import com.dxc.ssi.agent.didcomm.states.StateMachine
import com.dxc.ssi.agent.model.messages.Message

class  DidExchangeStateMachine: StateMachine {
    override fun getNextStateAndAction(currentState: State, message: Message): Pair<State, Action> {
        TODO("Not yet implemented")
    }


}
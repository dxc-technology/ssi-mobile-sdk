package com.dxc.ssi.agent.didcomm.states

import com.dxc.ssi.agent.didcomm.actions.Action
import com.dxc.ssi.agent.model.messages.Message

interface StateMachine {

    fun getNextStateAndAction(currentState: State, message: Message): Pair<State, Action>

}



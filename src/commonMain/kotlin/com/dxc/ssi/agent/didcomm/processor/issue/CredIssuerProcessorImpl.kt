package com.dxc.ssi.agent.didcomm.processor.issue

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.states.State
import com.dxc.ssi.agent.didcomm.states.StateMachine
import com.dxc.ssi.agent.didcomm.states.issue.CredentialIssuenceStateMachine

class CredIssuerProcessorImpl(walletConnector: WalletConnector,
                              transport: Transport
) : AbstractMessageProcessor(walletConnector, transport), CredIssuerProcessor {
    private val stateMachine = CredentialIssuenceStateMachine()
    //TODO: reuse kotlin possibility to override states to make abstract property instead of abstract method if possible
    override fun getStateMachine(): StateMachine {
        return stateMachine
    }

    override fun getCurrentState(): State {
        TODO("Not yet implemented")
    }
}

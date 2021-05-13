package com.dxc.ssi.agent.didcomm.processor.verify

import com.dxc.ssi.agent.api.pluggable.Transport
import com.dxc.ssi.agent.api.pluggable.wallet.WalletConnector
import com.dxc.ssi.agent.didcomm.processor.AbstractMessageProcessor
import com.dxc.ssi.agent.didcomm.states.State
import com.dxc.ssi.agent.didcomm.states.StateMachine
import com.dxc.ssi.agent.didcomm.states.verify.CredentialVerificationStateMachine

class CredVerifierProcessorImpl(walletConnector: WalletConnector,
                                transport: Transport
) : AbstractMessageProcessor(walletConnector, transport), CredVerifierProcessor {

    private val stateMachine = CredentialVerificationStateMachine()
    //TODO: reuse kotlin possibility to override states to make abstract property instead of abstract method if possible
    override fun getStateMachine(): StateMachine {
        return stateMachine
    }

    override fun getCurrentState(): State {
        TODO("Not yet implemented")
    }
}

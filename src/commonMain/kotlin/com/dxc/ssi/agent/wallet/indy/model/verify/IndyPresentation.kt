package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.didcomm.model.verify.data.Presentation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndyPresentation(
    @SerialName("proof") val indyProof: IndyProof,
    @SerialName("requested_proof") val requestedProof: RequestedProof,
    val identifiers: List<ProofIdentifier>
) : Presentation

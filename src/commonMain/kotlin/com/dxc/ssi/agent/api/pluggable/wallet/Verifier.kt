package com.dxc.ssi.agent.api.pluggable.wallet

/**
 * This interface represents verifier - an entity which purpose is to verify someone's credentials.
 * It has a read only access to public ledger.
 */
interface Verifier {
    /**
     * Verifies proof produced by prover
     *
     * @param proofReq [ProofRequest] - proof request used by prover to create proof
     * @param proof [ProofInfo] - proof created by prover
     * @param usedData [DataUsedInProofJson] - some data from ledger needed to verify proof
     *
     * @return [Boolean] - is proof valid?
     */
 //   fun verifyProof(proofReq: ProofRequest, proof: ProofInfo, usedData: DataUsedInProofJson): Boolean
}
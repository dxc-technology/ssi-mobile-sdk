/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.usecase

import com.luxoft.blockchainlab.hyperledger.indy.models.ProofRequest
import com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.irepository.IndyRepository
import io.reactivex.Single

/**
 * Use case to interact between UI and data repository.
 * Holder sends proof to Verifier.
 * */
class SendProofUseCase constructor(
        private val indyRepository: IndyRepository
) {

    fun get(proofRequest: ProofRequest): Single<String> =
            indyRepository.sendProof(proofRequest)
}
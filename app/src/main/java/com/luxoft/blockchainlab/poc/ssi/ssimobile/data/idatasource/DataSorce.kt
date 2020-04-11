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

package com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource

import android.graphics.Bitmap
import com.luxoft.blockchainlab.corda.hyperledger.indy.IndyPartyConnection
import com.luxoft.blockchainlab.hyperledger.indy.models.ProofRequest
import io.reactivex.Single

/**
 * Data source interface.
 * For methods to work with local data storage
 * */
interface LocalDataSource {
}

/**
 * Data source interface.
 * Methods to interact between indy users.
 * */
interface RemoteDataSource {
    fun getCredentials(url : String): Single<String>
    fun sendProofOnRequest(url: String): Single<String>
    fun receiveProofRequest(url: String): Single<ProofRequest>
    fun sendProof(proofRequest: ProofRequest): Single<String>
    fun getInviteQRCode(): Single<Bitmap>
    fun waitForInvitedParty(timeout : Long): Single<IndyPartyConnection>
    fun sendProofRequestReceiveAndVerify(indyPartyConnection: IndyPartyConnection?): Single<Boolean>
}
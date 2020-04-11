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

package com.luxoft.blockchainlab.poc.ssi.ssimobile.data.repository

import android.graphics.Bitmap
import com.luxoft.blockchainlab.corda.hyperledger.indy.IndyPartyConnection
import com.luxoft.blockchainlab.hyperledger.indy.models.ProofRequest
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource.LocalDataSource
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource.RemoteDataSource
import com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.irepository.IndyRepository
import io.reactivex.Single

/**
 * Implementation of repository interface.
 * */
class IndyRepositoryImpl constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) :
    IndyRepository {

    override fun getCredentials(url: String): Single<String> =
            remoteDataSource.getCredentials(url)

    override fun sendProofOnRequest(url: String): Single<String> =
            remoteDataSource.sendProofOnRequest(url)

    override fun receiveProofRequest(url: String): Single<ProofRequest> =
            remoteDataSource.receiveProofRequest(url)

    override fun sendProof(proofRequest: ProofRequest): Single<String> =
            remoteDataSource.sendProof(proofRequest)

    override fun getInviteQRCode(): Single<Bitmap> =
            remoteDataSource.getInviteQRCode()

    override fun waitForInvitedParty(timeout: Long): Single<IndyPartyConnection> =
            remoteDataSource.waitForInvitedParty(timeout)

    override fun sendProofRequestReceiveAndVerify(indyPartyConnection: IndyPartyConnection?): Single<Boolean>  =
            remoteDataSource.sendProofRequestReceiveAndVerify(indyPartyConnection)

}
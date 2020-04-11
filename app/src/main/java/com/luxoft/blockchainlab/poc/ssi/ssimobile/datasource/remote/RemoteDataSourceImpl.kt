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

package com.luxoft.blockchainlab.poc.ssi.ssimobile.datasource.remote

import android.graphics.Bitmap
import com.luxoft.blockchainlab.corda.hyperledger.indy.AgentConnection
import com.luxoft.blockchainlab.hyperledger.indy.models.ProofInfo
import com.luxoft.blockchainlab.hyperledger.indy.models.ProofRequest
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.ApplicationState
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.SharedPreferencesStore
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource.RemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.luxoft.blockchainlab.corda.hyperledger.indy.IndyPartyConnection
import com.luxoft.blockchainlab.hyperledger.indy.models.PredicateTypes
import com.luxoft.blockchainlab.hyperledger.indy.models.RevealedAttributeReference
import com.luxoft.blockchainlab.hyperledger.indy.utils.*
import com.luxoft.blockchainlab.poc.ssi.ssimobile.application.*
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.Invite
import java.util.*
import java.util.Calendar.*

/**
 * Data source interface implementation.
 * Methods to interact between indy users.
 * */
class RemoteDataSourceImpl constructor(
    private val agentConnection: AgentConnection,
    private val applicationState: ApplicationState,
    private val sharedPreferencesStore: SharedPreferencesStore
) : RemoteDataSource {

    //Holder gets credentials from Issuer
    override fun getCredentials(url: String): Single<String> {
        return Single.create<String> { s ->
            run {
                try {
                    agentConnection.acceptInvite(SerializationUtils.jSONToAny<Invite>(url).invite)
                        .toBlocking().value().apply {
                            do {
                                val credOffer = try {
                                    receiveCredentialOffer().timeout(5, TimeUnit.SECONDS)
                                        .toBlocking()
                                        .value()
                                } catch (e: RuntimeException) {
                                    if (e.cause !is TimeoutException)
                                        throw e
                                    null
                                }?.apply {
                                    val indyUser = applicationState.indyState.indyUser.value!!
                                    val credentialRequest = indyUser.createCredentialRequest(
                                        indyUser.walletUser.getIdentityDetails().did,
                                        this
                                    )
                                    sendCredentialRequest(credentialRequest)
                                    val credential = receiveCredential().toBlocking().value()
                                    indyUser.checkLedgerAndReceiveCredential(
                                        credential,
                                        credentialRequest,
                                        this
                                    )
                                }
                            } while (credOffer != null)
                            applicationState.updateWalletCredentials()
                            s.onSuccess("completed")
                        }
                } catch (er: Exception) {
                    s.onError(er)
                }
            }
        }
    }

    //Holder receives proof request from Verifier and then sends proof back
    override fun sendProofOnRequest(url: String): Single<String> {
        return Single.create<String> { s ->
            run {
                try {
                    agentConnection.acceptInvite(SerializationUtils.jSONToAny<Invite>(url).invite)
                        .toBlocking().value().apply {
                            val proofRequest = receiveProofRequest().toBlocking().value()
                            val requestedData: Set<String> =
                                proofRequest.requestedAttributes.keys + proofRequest.requestedPredicates.keys
                            val requestedDataStr = requestedData.joinToString(separator = ", ")
                            sharedPreferencesStore.writeString(
                                sharedPreferencesRequstedDataName,
                                sharedPreferencesRequstedDataKey,
                                requestedDataStr
                            )
                            val proofFromLedgerData: ProofInfo =
                                applicationState.indyState.indyUser.value!!.createProofFromLedgerData(
                                    proofRequest
                                )
                            sendProof(proofFromLedgerData)
                            s.onSuccess("completed")
                        }
                } catch (er: Exception) {
                    s.onError(er)
                }
            }
        }
    }

    //Holder receives proof request from Verifier
    override fun receiveProofRequest(url: String): Single<ProofRequest> {
        return Single.create<ProofRequest> { s ->
            run {
                try {
                    agentConnection.acceptInvite(url).toBlocking().value().apply {
                        val proofRequest = receiveProofRequest().toBlocking().value()
                        sharedPreferencesStore.writeString(
                            sharedPreferencesLastConnectionDiDName,
                            sharedPreferencesLastConnectionDiDKey,
                            this.partyDID()
                        )
                        s.onSuccess(proofRequest)
                    }
                } catch (er: Exception) {
                    s.onError(er)
                }
            }
        }
    }

    //Holder sends proof to Verifier
    override fun sendProof(proofRequest: ProofRequest): Single<String> {
        return Single.create<String> { s ->
            run {
                try {
                    val requestedData: Set<String> =
                        proofRequest.requestedAttributes.keys + proofRequest.requestedPredicates.keys
                    val requestedDataStr = requestedData.joinToString(separator = ", ")
                    sharedPreferencesStore.writeString(
                        sharedPreferencesRequstedDataName,
                        sharedPreferencesRequstedDataKey,
                        requestedDataStr
                    )
                    val did = sharedPreferencesStore.readString(
                        sharedPreferencesLastConnectionDiDName,
                        sharedPreferencesLastConnectionDiDKey
                    )
                    val proofFromLedgerData: ProofInfo =
                        applicationState.indyState.indyUser.value!!.createProofFromLedgerData(
                            proofRequest
                        )
                    agentConnection.getIndyPartyConnection(did!!).toBlocking().value().apply {
                        this!!.sendProof(proofFromLedgerData)
                        s.onSuccess("completed")
                    }
                } catch (er: Exception) {
                    s.onError(er)
                }
            }
        }
    }

    //Indy user gets QR-code containing URL to invite other indy user to connect
    override fun getInviteQRCode(): Single<Bitmap> {
        return Single.create<Bitmap> { bitmap ->
            run {
                val inviteUrl: String = agentConnection.generateInvite().toBlocking().value();
                sharedPreferencesStore.writeString(
                    sharedPreferencesLastInviteUrlName,
                    sharedPreferencesLastInviteUrlKey,
                    inviteUrl
                )
                val multiFormatWriter = MultiFormatWriter()
                try {
                    val bitMatrix =
                        multiFormatWriter.encode(inviteUrl, BarcodeFormat.QR_CODE, 200, 200)
                    val barcodeEncoder = BarcodeEncoder()
                    bitmap.onSuccess(barcodeEncoder.createBitmap(bitMatrix))
                } catch (e: WriterException) {
                    e.printStackTrace()
                    bitmap.onError(e)
                }
            }
        }
    }

    //Indy user waits connection after invitation sending
    override fun waitForInvitedParty(timeout: Long): Single<IndyPartyConnection> {
        return Single.create<IndyPartyConnection> { connection ->
            run {
                try {
                    connection.onSuccess(
                        agentConnection.waitForInvitedParty(
                            sharedPreferencesStore.readString(
                                sharedPreferencesLastInviteUrlName,
                                sharedPreferencesLastInviteUrlKey
                            )!!, timeout
                        ).toBlocking().value()
                    )
                } catch (e: Exception) {
                    if (e is IllegalStateException) {
                        connection.isDisposed
                    } else {
                        connection.onError(e)
                    }
                }
            }
        }
    }

    //Verifier sends proof request to Holder, receives proof and verify it
    override fun sendProofRequestReceiveAndVerify(indyPartyConnection: IndyPartyConnection?): Single<Boolean> {
        return Single.create<Boolean> { boolean ->
            run {
                try {
                    val proofRequest: ProofRequest = createProofRequest()
                    indyPartyConnection!!.sendProofRequest(proofRequest).apply {
                        indyPartyConnection.receiveProof().toBlocking().value().apply {
                            val verified: Boolean =
                                applicationState.indyState.indyUser.value!!.verifyProofWithLedgerData(
                                    proofRequest,
                                    this
                                )
                            reference = getAttributeValue("photo")!!
                            boolean.onSuccess(verified)
                        }
                    }
                } catch (e: Exception) {
                    boolean.onSuccess(false)
                }
            }
        }

    }

    companion object {
        lateinit var reference: RevealedAttributeReference
        var ageGroup: Int = 0
    }

    private fun createProofRequest(): ProofRequest {
        val proofReq = proofRequest("proof_req", "0.1") {
            reveal("photo")
            provePredicateThan(
                "Birth_Date",
                PredicateTypes.LT,
                getTimestampOfLastDateForBirth(ageGroup)
            )
        }
        return proofReq;
    }

    fun getTimestampOfLastDateForBirth(ageGroup: Int): Int {
        var currentDate = GregorianCalendar()
        var year = currentDate.get(YEAR)
        var month = currentDate.get(MONTH)
        var day = currentDate.get(DAY_OF_MONTH)
        var birthDate = GregorianCalendar()
        birthDate.set(YEAR, year - ageGroup)
        birthDate.set(MONTH, month)
        birthDate.set(DAY_OF_MONTH, day)
        var ret = (birthDate.timeInMillis / 1000).toInt()
        return ret
    }
}
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

package com.luxoft.blockchainlab.poc.ssi.ssimobile.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import com.luxoft.blockchainlab.hyperledger.indy.models.CredentialReference
import com.luxoft.blockchainlab.poc.ssi.ssimobile.R
import com.luxoft.blockchainlab.poc.ssi.ssimobile.utils.MutableLiveData
import com.luxoft.blockchainlab.poc.ssi.ssimobile.utils.VolatileLiveDataHolder
import com.luxoft.blockchainlab.poc.ssi.ssimobile.utils.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ApplicationState(
    val context: Context,
    val indyState: IndyState)
{
    private val refreshedIndyUser = VolatileLiveDataHolder(indyState.indyUser)
    val walletCredentials: LiveData<List<CredentialReference>> = refreshedIndyUser.liveData.map { indyUser ->
        indyUser.walletUser.getCredentials().asSequence().toList()
    }

    val user: LiveData<UserState> =
        walletCredentials.map { creds ->
            var fullName: String? = null
            var profilePic: Drawable? = null
            var firstName: String? = null
            var birthDate: String? = null
            var photo: String? = null
            var secondName: String? = null
            var number: String? = null

            // ignores credentials of the same schema
            val availableSchemas = creds.associateBy { it.getSchemaIdObject().name }

            if(KnownSchemas.PatientId.schemaName in availableSchemas) {
                val credRef = availableSchemas[KnownSchemas.PatientId.schemaName]
                fullName = credRef?.attributes?.get(KnownSchemas.PatientId.attributes.name)?.toString()
                profilePic = context.getDrawable(R.drawable.user)
            }

            if(KnownSchemas.PersonalId.schemaName in availableSchemas) {
                val credRef = availableSchemas[KnownSchemas.PersonalId.schemaName]
                firstName = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.firstName)?.toString()
                birthDate = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.birthDate)?.toString()
                photo = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.photo)?.toString()
                secondName = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.secondName)?.toString()
                number = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.number)?.toString()
            }

            UserState(fullName, profilePic, firstName, birthDate, photo, secondName, number)
        }

    private val mutAuthenticationHistory = MutableLiveData(initialValue = listOf<VerificationEvent>())
    val authenticationHistory: LiveData<List<VerificationEvent>> = mutAuthenticationHistory

    fun updateWalletCredentials() {
        GlobalScope.launch(Dispatchers.Main) {
            refreshedIndyUser.refresh()
        }
    }

    fun clearAuthenticationHistory() {
        GlobalScope.launch(Dispatchers.Main) {
            mutAuthenticationHistory.value = listOf()
        }
    }

    fun clearLocalData() {
        indyState.resetWallet()
        clearAuthenticationHistory()
    }

    fun storeVerificationEvent(event: VerificationEvent) {
        val oldList = mutAuthenticationHistory.value ?: listOf()
        GlobalScope.launch(Dispatchers.Main) {
            mutAuthenticationHistory.value = oldList + listOf(event)
        }
    }
}


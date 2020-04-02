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
import androidx.lifecycle.LiveData
import com.luxoft.blockchainlab.hyperledger.indy.models.CredentialReference
import com.luxoft.blockchainlab.poc.ssi.ssimobile.utils.VolatileLiveDataHolder
import com.luxoft.blockchainlab.poc.ssi.ssimobile.utils.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.net.URI

class ApplicationState(
        indyPoolIp: InetAddress,
        indyPoolGenesisPath: URI,
        indyPoolTailsPath: URI,
        indyPoolGenesisContent: (nodeIp: InetAddress) -> String = ::StandardIndyPoolGenesis)
{
    val indyState: IndyState = IndyState(
            indyPoolIp,
            indyPoolGenesisPath,
            indyPoolTailsPath,
            indyPoolGenesisContent)

    private val refreshedIndyUser = VolatileLiveDataHolder(indyState.indyUser)
    val walletCredentials: LiveData<List<CredentialReference>> = refreshedIndyUser.liveData.map { indyUser ->
        indyUser.walletUser.getCredentials().asSequence().toList()
    }

    lateinit var context: Context
    val user: LiveData<UserState> =
            walletCredentials.map { creds ->
                val credRef = creds.firstOrNull { it.getSchemaIdObject().name == KnownSchemas.PersonalId.schemaName }
                val firstName = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.firstName)?.toString()
                val birthDate = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.birthDate)?.toString()
                val photo = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.photo)?.toString()
                val secondName = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.secondName)?.toString()
                val number = credRef?.attributes?.get(KnownSchemas.PersonalId.attributes.number)?.toString()

                UserState(firstName, birthDate, photo, secondName, number)
            }


    fun updateWalletCredentials() {
        GlobalScope.launch(Dispatchers.Main) {
            refreshedIndyUser.refresh()
        }
    }

}


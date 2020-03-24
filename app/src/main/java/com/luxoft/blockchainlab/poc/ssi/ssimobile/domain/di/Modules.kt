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

package com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.di

import android.os.Environment
import com.luxoft.blockchainlab.corda.hyperledger.indy.AgentConnection
import com.luxoft.blockchainlab.corda.hyperledger.indy.PythonRefAgentConnection
import com.luxoft.blockchainlab.poc.ssi.ssimobile.application.*
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.ApplicationState
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.SharedPreferencesStore
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource.LocalDataSource
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.idatasource.RemoteDataSource
import com.luxoft.blockchainlab.poc.ssi.ssimobile.data.repository.IndyRepositoryImpl
import com.luxoft.blockchainlab.poc.ssi.ssimobile.datasource.local.LocalDataSourceImpl
import com.luxoft.blockchainlab.poc.ssi.ssimobile.datasource.remote.RemoteDataSourceImpl
import com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.irepository.IndyRepository
import com.luxoft.blockchainlab.poc.ssi.ssimobile.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import java.net.InetAddress

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            sharedPreferencesStoreModule,
            applicationsStateModule,
            agentConnectionModule
    )
}

val useCaseModule: Module = module {
    factory { GetCredentialsUseCase(indyRepository = get()) }
    factory { GetProofRequestUseCase(indyRepository = get()) }
    factory { SendProofUseCase(indyRepository = get()) }
    factory { GetInviteQRCodeUseCase(indyRepository = get()) }
    factory { WaitForInvitedPartyUseCase(indyRepository = get()) }
    factory { SendProofRequestReceiveVerifyUseCase(indyRepository = get()) }
}

val repositoryModule: Module = module {
    single { IndyRepositoryImpl(localDataSource = get(), remoteDataSource = get()) as IndyRepository }
}

val applicationsStateModule: Module = module {
    single<ApplicationState> {
        applicationState.context = androidContext()
        applicationState
    }
}

val phoneStorage = Environment.getExternalStorageDirectory().toURI()

val applicationState = ApplicationState(
        InetAddress.getByName(GENESIS_IP),
        phoneStorage.resolve(GENESIS_PATH),
        phoneStorage.resolve(TAILS_PATH)
)

val dataSourceModule: Module = module {
    single { RemoteDataSourceImpl(agentConnection = agentConnection, applicationState = applicationState, sharedPreferencesStore = get()) as RemoteDataSource }
    single { LocalDataSourceImpl() as LocalDataSource }
}

val sharedPreferencesStoreModule: Module = module {
    single { SharedPreferencesStore(androidContext()) }
}



val agentConnectionModule: Module = module {
    single<AgentConnection>(createdAtStart = true) {
        agentConnection
    }
}

val agentConnection = PythonRefAgentConnection()
val agentConnectionProgress = agentConnection.connect(WS_ENDPOINT, WS_LOGIN, WS_PASS).toBlocking().value()



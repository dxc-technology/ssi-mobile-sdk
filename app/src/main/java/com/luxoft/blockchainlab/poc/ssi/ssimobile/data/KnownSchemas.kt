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

/**
 * Information about Indy schemas that needs to be known for the app to function.
 * May need updating after creation of new schemas.
 * */
object KnownSchemas {
    object PersonalId {
        val schemaName = "Swiss Travel Pass"

        object attributes {
            val firstName = "First_Name"
            val birthDate = "Birth_Date"
            val photo = "Photo"
            val secondName = "Second_Name"
            val number = "Swiss_Pass_Num"
        }
    }

    object Common {
        object attributes {
            val type = "Credential_Type"
            val name = "Credential_Name"
            val issuer = "Credential_Issuer"
        }
    }
}
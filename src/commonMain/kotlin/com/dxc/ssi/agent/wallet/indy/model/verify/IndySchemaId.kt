package com.dxc.ssi.agent.wallet.indy.model.verify

import com.dxc.ssi.agent.didcomm.model.verify.data.SchemaId
import com.dxc.ssi.agent.wallet.indy.model.issue.temp.FromString

/**
 * Schema id is the local identifier of some schema. Don't confuse with schema sequence number which represents schema's
 * index on ledger. This class (de)serializes schema id.
 * Indy uses raw string value of schema id, but it's parts are actually very useful. By possessing some schema id one
 * could also know its:
 *
 * @param did: [String] - the DID of the schema issuer
 * @param name: [String] - the name of the schema
 * @param version: [String] - the version of the schema
 */
class IndySchemaId(val did: String, val name: String, val version: String): SchemaId {
    override fun toString() = "$did:2:$name:$version"

    companion object : FromString<IndySchemaId> {
        override fun fromString(str: String): IndySchemaId {
            val (did, _, name, version) = str.split(":")

            return IndySchemaId(did, name, version)
        }
    }
}

package com.dxc.ssi.agent.wallet.indy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * {
 *     "id": string, Identifier of the wallet. Configured storage uses this identifier to lookup exact wallet data placement.
 *
 *     "storage_type": optional<string>, Type of the wallet storage. Defaults to 'default'.
 *     'Default' storage type allows to store wallet data in the local file.
 *     Custom storage types can be registered with indy_register_wallet_storage call.
 *
 *     "storage_config": optional<object>, Storage configuration json. Storage type defines set of supported keys.
 *     Can be optional if storage supports default configuration.
 *
 *     For 'default' storage type configuration is:
 *     {
 *         "path": optional<string>, Path to the directory with wallet files.
 *         Defaults to $HOME/.indy_client/wallets.
 *         Wallet will be stored in the file {path}/{id}/sqlite.db
 *     }
 * }
 */

@Serializable
data class WalletConfig(
    val id: String,
    @SerialName("storage_type") val storageType: String = "default",
    @SerialName("storage_config")val storageConfig: StorageConfig? = null
)
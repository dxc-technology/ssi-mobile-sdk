package helpers


/**
 * Helps to manage wallets
 */
//TODO: review this functionality and decide if it can be moved to common layer
object WalletHelper {

    fun createNonExisting(config: WalletConfig, password: WalletPassword) {
        val walletConfigJson = "{}" //SerializationUtils.anyToJSON(config)
        val walletPasswordJson = "{}" // SerializationUtils.anyToJSON(password)

        //Wallet.createWallet(walletConfigJson, walletPasswordJson) //.get()
    }

    fun createNonExisting(walletName: String, walletPassword: String) {
        createNonExisting(WalletConfig(walletName), WalletPassword(walletPassword))
    }

    fun openExisting(config: WalletConfig, password: WalletPassword): Int {
        //if (!exists(config.id))
        //    throw FileNotFoundException("Wallet ${EnvironmentUtils.getIndyWalletPath(config.id)} doesn't exist")
        val walletConfigJson = "{}" //SerializationUtils.anyToJSON(config)
        val walletPasswordJson = "{}"//SerializationUtils.anyToJSON(password)
        return 1 //(Wallet.createWallet(walletConfigJson, walletPasswordJson)).toInt() // .get()
    }

    fun openOrCreate(config: WalletConfig, password: WalletPassword): Int {
        //if (!exists(config.id))
        //createNonExisting(config, password)

        return openExisting(config, password)
    }

    fun openOrCreate(walletName: String, walletPassword: String): Int =
        openOrCreate(WalletConfig(walletName), WalletPassword(walletPassword))
}

data class WalletConfig(
    val id: String,
    val storageType: String = "default",
    val storageConfig: StorageConfig? = null
)

/**
 * Allows to define custom wallet storage path
 */
data class StorageConfig(val path: String)

/**
 * Represents wallet auth key
 */
data class WalletPassword(val key: String)

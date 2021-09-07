package com.dxc.ssi.agent.exceptions.indy

//TODO: looks like indy codes should go to common class, rework this
actual open class IndyException(override val message:String, val sdkErrorCode: Int): Exception(message) {
    val sdkMessage: String?
    val sdkBackTrace: String?

    init {
        val errorDetails = ErrorDetails.getErrorDetailsFromIndy()
        sdkMessage = errorDetails.message
        sdkBackTrace = errorDetails.backtrace
    }

    override fun toString(): String {
        return "${super.toString()} " +
                "\n sdkMessage = $sdkMessage" +
                "\n sdkBacktrace = $sdkBackTrace"
    }

    companion object {

        /**
         * Initializes a new IndyException using the specified SDK error code.
         *
         * @param sdkErrorCode The SDK error code to construct the exception from.
         *
         * @return IndyException correspondent to SDK error code
         */
        fun fromSdkError(sdkErrorCode: Int): IndyException {
            val errorCode = ErrorCode.valueOf(sdkErrorCode)
         //   val errorDetails = ErrorDetails.getErrorDetailsFromIndy()
            return when (errorCode) {
                ErrorCode.CommonInvalidParam1,
                ErrorCode.CommonInvalidParam2,
                ErrorCode.CommonInvalidParam3,
                ErrorCode.CommonInvalidParam4,
                ErrorCode.CommonInvalidParam5,
                ErrorCode.CommonInvalidParam6,
                ErrorCode.CommonInvalidParam7,
                ErrorCode.CommonInvalidParam8,
                ErrorCode.CommonInvalidParam9,
                ErrorCode.CommonInvalidParam10,
                ErrorCode.CommonInvalidParam11,
                ErrorCode.CommonInvalidParam12,
                ErrorCode.CommonInvalidParam13,
                ErrorCode.CommonInvalidParam14 -> InvalidParameterException(sdkErrorCode)
                ErrorCode.WalletItemNotFound -> WalletItemNotFoundException()
                ErrorCode.AnoncredsMasterSecretDuplicateNameError -> DuplicateMasterSecretNameException()
                /*
                TODO: implement those exceptions when we need them
                ErrorCode.CommonInvalidStructure -> InvalidStructureException()
                ErrorCode.CommonIOError -> IOException()
                ErrorCode.WalletInvalidHandle -> InvalidWalletException()
                ErrorCode.WalletUnknownTypeError -> UnknownWalletTypeException()
                ErrorCode.WalletTypeAlreadyRegisteredError -> DuplicateWalletTypeException()
                */
                ErrorCode.WalletAlreadyExistsError -> WalletExistsException()
                ErrorCode.PoolLedgerTimeout -> PoolLedgerTimeoutException()
                ErrorCode.CommonInvalidState -> InvalidStateException()
                /*
                ErrorCode.WalletNotFoundError -> WalletNotFoundException()
                ErrorCode.WalletInputError -> WalletInputException()
                ErrorCode.WalletDecodingError -> WalletDecodingException()
                ErrorCode.WalletStorageError -> WalletStorageException()
                ErrorCode.WalletEncryptionError -> WalletEncryptionException()
                ErrorCode.WalletItemAlreadyExists -> WalletItemAlreadyExistsException()
                ErrorCode.WalletQueryError -> WalletInvalidQueryException()
                ErrorCode.WalletIncompatiblePoolError -> WrongWalletForPoolException()
                ErrorCode.WalletAlreadyOpenedError -> WalletAlreadyOpenedException()
                ErrorCode.WalletAccessFailed -> WalletAccessFailedException()
                ErrorCode.PoolLedgerNotCreatedError -> PoolConfigNotCreatedException()
                ErrorCode.PoolLedgerInvalidPoolHandle -> InvalidPoolException()
                ErrorCode.PoolLedgerTerminated -> PoolLedgerTerminatedException()
                ErrorCode.LedgerNoConsensusError -> ConsensusException()
                ErrorCode.LedgerInvalidTransaction -> LedgerInvalidTransactionException()
                ErrorCode.LedgerSecurityError -> LedgerSecurityException()
                ErrorCode.PoolLedgerConfigAlreadyExistsError -> PoolLedgerConfigExistsException()
                ErrorCode.PoolLedgerTimeout -> ledger.TimeoutException()
                ErrorCode.PoolIncompatibleProtocolVersion -> PoolIncompatibleProtocolVersionException()
                ErrorCode.LedgerNotFound -> LedgerNotFoundException()
                ErrorCode.AnoncredsRevocationRegistryFullError -> RevocationRegistryFullException()
                ErrorCode.AnoncredsInvalidUserRevocId -> AnoncredsInvalidUserRevocId()

                ErrorCode.AnoncredsProofRejected -> ProofRejectedException()
                ErrorCode.AnoncredsCredentialRevoked -> CredentialRevokedException()
                ErrorCode.AnoncredsCredDefAlreadyExistsError -> CredDefAlreadyExistsException()
                ErrorCode.UnknownCryptoTypeError -> UnknownCryptoException()
                ErrorCode.DidAlreadyExistsError -> DidAlreadyExistsException()
                ErrorCode.UnknownPaymentMethod -> UnknownPaymentMethodException()
                ErrorCode.IncompatiblePaymentError -> IncompatiblePaymentException()
                ErrorCode.InsufficientFundsError -> InsufficientFundsException()
                ErrorCode.ExtraFundsError -> ExtraFundsException()
                ErrorCode.PaymentSourceDoesNotExistError -> PaymentSourceDoesNotExistException()
                ErrorCode.PaymentOperationNotSupportedError -> PaymentOperationNotSupportedException()
                ErrorCode.TransactionNotAllowedError -> TransactionNotAllowedException()
                 */
                else -> {
                    val message =
                        "An unmapped error with the code $sdkErrorCode was returned by the SDK."
                    IndyException(message, sdkErrorCode)
                }
            }
        }
    }
    //   1) setting environment variable `RUST_BACKTRACE=1`
    //   2) calling `setRuntimeConfig` API function with `collect_backtrace: true`
    /**
     * Initializes a new IndyException with the specified message.
     *
     * @param message The message for the exception.
     * @param sdkErrorCode The SDK error code to construct the exception from.
     */

}
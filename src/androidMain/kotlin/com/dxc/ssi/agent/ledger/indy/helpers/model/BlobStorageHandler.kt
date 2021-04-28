package com.dxc.ssi.agent.ledger.indy.helpers.model

import org.hyperledger.indy.sdk.blob_storage.BlobStorageReader
import org.hyperledger.indy.sdk.blob_storage.BlobStorageWriter

/**
 * Abstracts blob storage reader and writer which are used for tails file management
 */
data class BlobStorageHandler(val reader: BlobStorageReader, val writer: BlobStorageWriter)
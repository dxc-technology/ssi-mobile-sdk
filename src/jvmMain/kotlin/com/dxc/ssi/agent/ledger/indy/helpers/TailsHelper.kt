package com.dxc.ssi.agent.ledger.indy.helpers

import com.dxc.ssi.agent.ledger.indy.helpers.model.BlobStorageHandler
import com.dxc.ssi.agent.ledger.indy.helpers.model.TailsConfig
import com.dxc.ssi.agent.ledger.indy.helpers.model.TailsRequest
import com.dxc.ssi.agent.ledger.indy.helpers.model.TailsResponse
import com.dxc.ssi.agent.wallet.indy.utils.SerializationUtils
import org.hyperledger.indy.sdk.blob_storage.BlobStorageReader
import org.hyperledger.indy.sdk.blob_storage.BlobStorageWriter
import java.io.File
import java.nio.file.Paths

/**
 * Helps to manage tails file
 */
object TailsHelper {
    /**
     * Returns [BlobStorageHandler] for some [tailsPath]
     *
     * Creates missed directories in path
     *
     * @param [tailsPath]: [String] - path to tails file directory (actual tails file will be named by its hash)
     * @return: [BlobStorageHandler]
     */
    fun getTailsHandler(tailsPath: String): BlobStorageHandler {
        val tailsConfig = SerializationUtils.anyToJSON(TailsConfig(tailsPath))

        File(tailsPath).mkdirs()

        val reader = BlobStorageReader.openReader("default", tailsConfig).get()
        val writer = BlobStorageWriter.openWriter("default", tailsConfig).get()

        return BlobStorageHandler(reader, writer)
    }
    class DefaultWriter (private val path: String) {
        fun write(tailsResponse: TailsResponse) {
            val dir = File(path)
            if (!dir.exists())
                dir.mkdirs()
            tailsResponse.tails.forEach { name, content ->
                /**
                 * Non-alphanumeric symbols are not allowed (files are named by tailsHash)
                 */
                if (name.toCharArray().any { !it.isLetterOrDigit() })
                    throw RuntimeException("Invalid character in the tails filename. Tails files must be named by hash.")
                val file = Paths.get(path, name).toFile()
                if (file.exists())
                    file.delete()
                file.createNewFile()
                file.writeBytes(content)
            }
        }
    }
    class DefaultReader(private val path: String) {
        fun read(tailsRequest: TailsRequest) : TailsResponse {
            if (tailsRequest.tailsHash.toCharArray().any { !it.isLetterOrDigit() })
                throw RuntimeException("Invalid character in the tails hash.")
            val file = Paths.get(path, tailsRequest.tailsHash).toFile()
            return if (!file.exists())
                TailsResponse(tailsRequest.tailsHash, mapOf())
            else
                TailsResponse(tailsRequest.tailsHash, mapOf(tailsRequest.tailsHash to file.readBytes()))
        }
    }
}
package com.dxc.ssi.agent.exceptions.indy

import com.indylib.indy_get_current_error
import kotlinx.cinterop.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class ErrorDetails(
    val message: String? = null,
    val backtrace: String? = null
) {


    companion object {
        fun getErrorDetailsFromIndy(): ErrorDetails {

            var rawErrorDetails: String

            memScoped {
                //TODO: check what amount of bytes we need to allocate. For now it seems 2000 is enough
                val errorDetailsJson: CValuesRef<CPointerVar<ByteVar>> = allocArray(2000)

                errorDetailsJson.usePinned { pinned ->
                    indy_get_current_error(error_json_p = pinned.get())
                    rawErrorDetails = pinned.get().getPointer(memScope)[0]!!.toKString()
                   // println("${pinned.get().getPointer(memScope)[0]!!.toKString()}")
                    //TODO: check if it is safe to make return from usePinned and from memScoped
                    return Json.decodeFromString(rawErrorDetails)
                }

            }
        }
    }
}

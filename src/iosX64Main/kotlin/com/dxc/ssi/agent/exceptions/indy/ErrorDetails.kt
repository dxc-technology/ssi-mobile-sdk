package com.dxc.ssi.agent.exceptions.indy

class ErrorDetails( val message: String? = null,
                    val backtrace: String? = null)  {


    companion object {
        fun getErrorDetailsFromIndy():ErrorDetails {
            //TODO: implement indy call here
            return ErrorDetails("Some IndyError", null)
        }
    }

        init {
            // val errorDetailsJson: com.sun.jna.ptr.PointerByReference = com.sun.jna.ptr.PointerByReference()
            /*
            val errorDetailsJson2 : CValuesRef<CPointerVar<ByteVar>> = CPointer(NativePtr.)


            indy_get_current_error(error_json_p = errorDetailsJson2)


            indy_get_current_error(
                error_json_p: CValuesRef<CPointerVar<ByteVar /* = ByteVarOf<Byte> */> /* =CPointerVarOf<CPointer<ByteVar /* = ByteVarOf<Byte> */>> */>?): Unit { /* compiled code */ }


            LibIndy.api.indy_get_current_error(errorDetailsJson)
            try {
                val errorDetails: org.json.JSONObject = org.json.JSONObject(errorDetailsJson.getValue().getString(0))
                message = errorDetails.optString("message")
                backtrace = errorDetails.optString("backtrace")
            } catch (ignored: java.lang.Exception) {
                // Nothing to do
            }

             */
        }
    }

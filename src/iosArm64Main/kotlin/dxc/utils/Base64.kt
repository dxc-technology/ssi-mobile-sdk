package com.dxc.utils

import com.dxc.ssi.agent.ledger.indy.genesis.nsdata
import io.ktor.util.*


//TODO: either move ktor implementation on common level to avoid having multiple actualizations, or find somme other library or use native way
actual class Base64 {
    actual companion object {
        @OptIn(InternalAPI::class)
        actual fun base64StringToPlainString(base64String: String): String {


            return base64String.decodeBase64String()

        }

        @OptIn(InternalAPI::class)
        actual fun plainStringToBase64String(plainString: String): String {
/*  This is working version, but using ktor to align it with decoding
            val st: NSString = plainString as NSString
            val data: NSData = st.dataUsingEncoding(encoding = NSUTF8StringEncoding)!!
            return data.base64EncodedStringWithOptions(options = 0)

 */

            return plainString.encodeBase64()
        }
    }
}
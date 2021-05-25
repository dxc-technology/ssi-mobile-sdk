package com.dxc.ssi.agent.api.impl

import android.content.Context
import com.dxc.ssi.agent.api.Environment

actual class EnvironmentImpl(val context:Context) : Environment {
    actual override fun getWritableFolderInUserHome(): String {
        //TODO: it does not work with Android recommended way of getting externalFIlesDir. Fix it. Until then will be using deprecated method
        //return context.getExternalFilesDir(null)?.absolutePath!!
        return android.os.Environment.getExternalStorageDirectory().absolutePath
    }
}
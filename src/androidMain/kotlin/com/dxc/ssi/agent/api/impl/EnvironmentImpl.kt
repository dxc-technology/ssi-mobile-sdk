package com.dxc.ssi.agent.api.impl

import android.content.Context
import com.dxc.ssi.agent.api.Environment

actual class EnvironmentImpl(val context: Context) : Environment {
    actual override fun getWritableFolderInUserHome(): String {
        val path = context.getExternalFilesDir(null)?.absolutePath!!
        val endIndex = path.indexOf("/Android")
        return path.substring(0, endIndex)
    }
}
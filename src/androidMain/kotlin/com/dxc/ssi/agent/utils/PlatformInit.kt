package com.dxc.ssi.agent.utils

import android.os.Environment


actual class PlatformInit {
    actual fun init() {
        //TODO: make this configurable
        //TODO: stop using deprecated function
        System.setProperty("INDY_HOME", Environment.getExternalStorageDirectory().absolutePath)
    }
}
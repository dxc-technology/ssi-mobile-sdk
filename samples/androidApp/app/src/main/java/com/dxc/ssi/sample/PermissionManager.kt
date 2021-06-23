package com.dxc.ssi.sample

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionManager {

    val PERMISSION_REQUEST_CODE = 1

    val requiredPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    fun getMissingPermissions(requestedPermissions: Array<String>, context: Context) =
        requestedPermissions.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()


    fun requestMissingPermissions(requestedPermissions: Array<String>, context: Context, activity: Activity) {
        ActivityCompat.requestPermissions(activity, requestedPermissions, PERMISSION_REQUEST_CODE)
    }

    fun requestPermissionsIfNeeded(context: Context, activity: Activity) {


        val missingPermissions = getMissingPermissions(requiredPermissions, context)
        if (missingPermissions.isNotEmpty() && Build.VERSION.SDK_INT < 30)
            requestMissingPermissions(missingPermissions, context, activity)

    }
}
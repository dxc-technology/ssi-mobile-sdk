package com.dxc.ssi.sample

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasAllFilesPermission() = Environment.isExternalStorageManager()

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= 30 && !hasAllFilesPermission()) {
            println("Build version ${Build.VERSION.SDK_INT}")

            val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")

            startActivity(
                    Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            uri
                    )
            )
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager.requestPermissionsIfNeeded(this, this)

        findViewById<Button>(R.id.connect).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val application = applicationContext as SsiApplication

            val ssiAgentApi = application.getSsiAgent()

            val issuerInvitationUrl =
                    "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkVSak42cGpCM0NIcjFhYTlxTlRLdlFudzc1WHpCVEZWaUd4TWFITHRRNDVaIl0sInJlY2lwaWVudEtleXMiOlsiRzVCRUpuaG5pd1U2V3ZjemNYN2s0bWpjR0dRVVBoUXlMRmtvWDIya0tKUHoiXSwiQGlkIjoiMWUzMjRmZDEtOWI5Ni00YzY0LTg0NmItMzE3MDVjNzJlNjEwIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"

            val verifierInvitationUrl =
                    "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiSjVDa25XNDVycTd2azlxQkN5Rk1yaTd2WlVRd2ZEalNFOWlQZm4zZ3VLRTciXSwicmVjaXBpZW50S2V5cyI6WyIyRDV6SEZpZEVrNFpob1AzUm50dG54eXhXYjZ5TVNtWXFoWWtvZWtRbTdLNCJdLCJAaWQiOiJhOGIwN2MwYS1jYjUzLTRhYzEtODBmNS0zNzY0ZDgxYWNiOWQiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="


            ssiAgentApi.connect(issuerInvitationUrl)
            ssiAgentApi.connect(verifierInvitationUrl)

        }
    }


}
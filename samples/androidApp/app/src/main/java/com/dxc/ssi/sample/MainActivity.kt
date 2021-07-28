package com.dxc.ssi.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager.requestPermissionsIfNeeded(this, this)

        findViewById<Button>(R.id.connect).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val application = applicationContext as SsiApplication

            val ssiAgentApi = application.getSsiAgent()

            val invitationUrl =
                "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiNlg3WUxjUjdOcDhyZlNIWHdaUGJpdE5iNkVFRzJyOGZveFhpcXFnNFFCbjciXSwiQGlkIjoiOWNiYzEzMDgtMTY4ZC00NjkyLTk4YjAtZGJiZjdkMzIxYTM4IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"
            ssiAgentApi.connect(invitationUrl, true)
        }
    }


}
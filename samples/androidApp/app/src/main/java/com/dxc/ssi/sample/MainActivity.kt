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
                "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIkVaVFYzaXZFakhCekx6WVVpa3d2cTN5aHpvMUcydUM5MlZHeUtZSGRySkVaIl0sInJlY2lwaWVudEtleXMiOlsiQ1F1M2ZmaXJ3Zk5DSnRySlhwcXV2ZDdmWkdtbXY3VkE3TWg1eHB4RXhWTCJdLCJAaWQiOiJhYjAwOWUxOC1lOWZmLTQzYTctOTM5NC02M2IzZTY0ZmMyMTYiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
            ssiAgentApi.connect(invitationUrl, true)
        }
    }


}
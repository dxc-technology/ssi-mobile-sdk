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
                "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiMkQ2TjluTFVaVXhpWWRkalZUYVlCRTNHR0JEU1VHUlRmcXhSdVg3RjU3SGciXSwicmVjaXBpZW50S2V5cyI6WyI4SGZMOVlMSkNNSEN4OHRVcGN0UVJCTjk2WWhRejZvQWdCeHZuTW9hcWZXdCJdLCJAaWQiOiJlMTFmNjUzOC0wMzE5LTQ5NDUtYmFkNS1mYjFiM2IyZmVjZjAiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="

            ssiAgentApi.connect(invitationUrl)


        }
    }



}
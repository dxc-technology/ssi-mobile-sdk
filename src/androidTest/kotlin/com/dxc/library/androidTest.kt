package com.dxc.library

import com.dxc.ssi.agent.kermit.Kermit
import com.dxc.ssi.agent.kermit.LogcatLogger
import org.json.JSONObject
import org.junit.Ignore
import org.junit.Test
import java.io.FileReader


class ReadingTest {

    @Test
    @Ignore("Old configuration for logger")
    fun testReadingConfigurationFile() {
        val kermit = Kermit(LogcatLogger())

        try {
            val reader = FileReader("config/logger.config")
            val txt = reader.readText()
            val level = JSONObject(txt).get("level")
            reader.close()


            kermit.i("CustomTag") { "Message" }
        } catch (e: Exception) {
            kermit.i("CustomTag") { e.message.toString() }
        }
    }
}
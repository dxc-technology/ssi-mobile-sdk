package com.dxc.library

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import org.json.JSONObject
import org.junit.Ignore
import org.junit.Test
import java.io.FileReader


class ReadingTest {

    @Test
    @Ignore("Old configuration for logger")
    fun testReadingConfigurationFile() {
        try {
            val reader = FileReader("config/logger.config")
            val txt = reader.readText()
            val level = JSONObject(txt).get("level")
            reader.close()

            val kermit = Kermit(LogcatLogger())
            kermit.i("CustomTag") { "Message" }
        }
        catch (e:Exception)
        {
            println(e.message)
        }
    }
}
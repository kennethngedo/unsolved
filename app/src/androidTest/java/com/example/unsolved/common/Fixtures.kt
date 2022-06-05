package com.example.unsolved.common

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.URL

class Fixtures {
    companion object {
        fun getFileFromPath(obj: Any, fileName: String): File? {
            val classLoader: ClassLoader? = obj.javaClass.classLoader
            val resource: URL? = classLoader?.getResource(fileName)
            return if (resource != null) File(resource.path) else null
        }

        fun getFixtureAsString(obj: Any, fileName: String): String {
            val classLoader: ClassLoader? = obj.javaClass.classLoader
            val resource: URL? = classLoader?.getResource(fileName)
            return if (resource != null) {
                val br = BufferedReader(InputStreamReader(FileInputStream(resource.path)))
                val sb = StringBuilder()
                var line = br.readLine()
                while (line != null) {
                    sb.append(line)
                    line = br.readLine()
                }
                sb.toString()
            } else ""
        }
    }
}
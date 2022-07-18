package dev.jamile.newsapp.util

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

const val ASSET_BASE_PATH = "../app/src/test/java/dev/jamile/newsapp/assets/"

@Throws(IOException::class)
inline fun <reified T> readJsonFile(filename: String): T {
    val br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
    val sb = StringBuilder()
    var line: String? = br.readLine()
    while (line != null) {
        sb.append(line)
        line = br.readLine()
    }
    return Gson().fromJson(sb.toString(), T::class.java)
}
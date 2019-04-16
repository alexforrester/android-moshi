package com.digian.example.moshicodegen.data

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Alex Forrester on 01/04/2019.
 */
object JsonTestHelper {

    private const val ASSET_BASE_PATH = "../app/src/main/assets/"

    @Throws(IOException::class)
    fun readJson(filename: String): String {
        val br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
        val sb = StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }

        return sb.toString()
    }
}
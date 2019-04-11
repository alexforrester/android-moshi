package com.digian.example.moshicodegen

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Alex Forrester on 01/04/2019.
 */
object JsonHelper {

    val moshi = Moshi.Builder().build()
    private const val ASSET_BASE_PATH = "../app/src/main/res/assets/"

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

    @Throws(IOException::class, JsonDataException::class)
    inline fun <reified T: Any> parseJson(json: String, ofClass: Class<T>) : T {

        val jsonAdapter = this.moshi.adapter(ofClass)

        return  jsonAdapter.fromJson(json)!!
    }



}
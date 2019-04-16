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

    /**
     * Thanks to accepted answer here:
     * @see <a href="https://stackoverflow.com/questions/34122450/how-to-get-generic-parameter-class-in-kotlin">How to get generic parameter class in Kotlin</a>
     */
    @Throws(IOException::class, JsonDataException::class)
    inline fun <reified T: Any> parseJson(json: String, ofClass: Class<T>) : T {

        val jsonAdapter = this.moshi.adapter(ofClass)

        return jsonAdapter.fromJson(json)!!
    }

}
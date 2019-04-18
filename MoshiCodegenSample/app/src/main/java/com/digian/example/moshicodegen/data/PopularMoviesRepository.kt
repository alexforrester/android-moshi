package com.digian.example.moshicodegen.data

import android.content.Context
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.IOException
import java.io.InputStream


/**
 * Created by Alex Forrester on 17/04/2019.
 */

internal interface PopularMoviesRepository {
    fun getMovies(): PopularMovies
}

internal open class PopularMoviesRepositoryImpl(private val context: Context, private val moshi: Moshi = Moshi.Builder().build()) : PopularMoviesRepository {

    override fun getMovies(): PopularMovies {

        val moviesJson = getMovieJSON()
        return parseJson(moviesJson, PopularMovies::class.java)
    }

    /**
     * Thanks to accepted answer here:
     * @see <a href="https://stackoverflow.com/questions/34122450/how-to-get-generic-parameter-class-in-kotlin">How to get generic parameter class in Kotlin</a>
     */
    @Throws(IOException::class, JsonDataException::class)
    private inline fun <reified T: Any> parseJson(json: String, ofClass: Class<T>) : T {

        val jsonAdapter = moshi.adapter(ofClass)

        return jsonAdapter.fromJson(json)!!
    }

    private fun getMovieJSON(fileName : String = "popular_movies_list.json"): String {

        val inputStream = getInputStreamForJsonFile(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }

    @Throws(IOException::class)
    internal open fun getInputStreamForJsonFile(fileName: String): InputStream {
        return context.assets.open(fileName)
    }

}
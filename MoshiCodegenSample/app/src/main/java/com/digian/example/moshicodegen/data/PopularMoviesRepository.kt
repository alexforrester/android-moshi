package com.digian.example.moshicodegen.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.IOException
import java.io.InputStream
import androidx.lifecycle.MutableLiveData


/**
 * Created by Alex Forrester on 17/04/2019.
 */
internal interface PopularMoviesRepository {
    val popularMoviesLiveData: LiveData<PopularMovies>
    fun getMovies(): LiveData<List<Movie>>
    fun setMoviesData()
}

internal open class PopularMoviesRepositoryImpl(private val context: Context, private val moshi: Moshi = Moshi.Builder().build()) : PopularMoviesRepository {

    override val popularMoviesLiveData = MutableLiveData<PopularMovies>()
    var isInitialized : Boolean = false


    override fun setMoviesData() {
        val moviesJson = getMovieJSON()
        val popularMovies = parseJson(moviesJson, PopularMovies::class.java)
        popularMoviesLiveData.value = popularMovies
    }

    override fun getMovies() : LiveData<List<Movie>> {
        if (!isInitialized) {
            setMoviesData()
            isInitialized = true
        }

        val moviesLiveData = MutableLiveData<List<Movie>>()
        moviesLiveData.value = popularMoviesLiveData.value?.movies ?: emptyList()
        return moviesLiveData
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
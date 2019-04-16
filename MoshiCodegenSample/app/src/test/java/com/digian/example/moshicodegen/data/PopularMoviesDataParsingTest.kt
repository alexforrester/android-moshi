package com.digian.example.moshicodegen.data

import com.digian.example.moshicodegen.JsonHelper
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable

/**
 * Created by Alex Forrester on 11/04/2019.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PopularMoviesDataParsingTest {

    private lateinit var popularMoviesJson: String
    private val moshi = Moshi.Builder().build()
    private lateinit var moviePages: MoviePages

    @BeforeAll
    internal fun setUp() {
        popularMoviesJson = JsonTestHelper.readJson("popular_movies_list.json")
        moviePages = JsonHelper.parseJson(popularMoviesJson, MoviePages::class.java)
    }


    @Test
    internal fun `test top level parsing of popular movies`() {

        assertAll(
            //Test Top Level Movie Parsing
            Executable { assertEquals(356, moviePages.totalPages) },
            Executable { assertEquals(7107, moviePages.totalResults) },
            Executable { assertEquals(20, moviePages.movies.size) }
        )
    }

    @Test
    internal fun `test individual popular movie is parsed correctly`() {

        val movie = moviePages.movies[1]

        assertAll(

            //Test Individual film
            Executable { assertEquals(12691, movie.voteCount) },
            Executable { assertEquals(278, movie.id) },
            Executable { assertFalse(movie.video) },
            Executable { assertEquals(8.7, movie.voteAverage) },
            Executable { assertEquals("The Shawshank Redemption", movie.title) },
            Executable { assertEquals(35.447, movie.popularity) },
            Executable { assertEquals("/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg", movie.posterPath) },
            Executable { assertEquals("en", movie.originalLanguage) },
            Executable { assertEquals("The Shawshank Redemption", movie.originalTitle) },
            Executable { assertEquals(listOf(18,80), movie.genreIds) },
            Executable { assertEquals("/j9XKiZrVeViAixVRzCta7h1VU9W.jpg", movie.backdropPath) },
            Executable { assertEquals("Framed in the 1940s for the double murder of his wife and her lover, " +
                    "upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. " +
                    "During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- " +
                    "for his integrity and unquenchable sense of hope.", movie.overview) },
            Executable { assertEquals("1994-09-23", movie.releaseDate) }
        )
    }


}
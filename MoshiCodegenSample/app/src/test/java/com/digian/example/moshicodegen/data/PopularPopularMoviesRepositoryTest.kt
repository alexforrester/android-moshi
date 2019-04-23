package com.digian.example.moshicodegen.data

import androidx.lifecycle.*
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by Alex Forrester on 11/04/2019.
 */

private const val ASSET_BASE_PATH = "../app/src/main/assets/"

@ExtendWith(InstantExecutorExtension::class)
internal class PopularPopularMoviesRepositoryTest {

    private val popularMoviesRepository: PopularMoviesRepository = object :
        PopularMoviesRepositoryImpl(mockk()) {

        override fun getInputStreamForJsonFile(fileName: String): InputStream {
            return FileInputStream(ASSET_BASE_PATH + fileName)
        }
    }

    @Test
    internal fun `test live data updates of popular movies`() {

        val observer = mockk<Observer<PopularMovies>>()
        every{ observer.onChanged(any()) } just Runs

        popularMoviesRepository.popularMoviesLiveData.observe(MoviesLifeCycleOwner(), observer)
        popularMoviesRepository.setMoviesData()

        verify { observer.onChanged(ofType(PopularMovies::class)) }
    }

    @Test
    internal fun `test parsing of popular movies`() {

        popularMoviesRepository.setMoviesData()

        val popularMovies = popularMoviesRepository.popularMoviesLiveData.value

        assertAll(
            Executable { assertEquals(1, popularMovies?.totalPages) },
            Executable { assertEquals(20, popularMovies?.totalResults) },
            Executable { assertEquals(20, popularMovies?.movies?.size) }
        )
    }

    @Test
    internal fun `test individual popular movie is parsed correctly`() {

        popularMoviesRepository.setMoviesData()

        val popularMovies = popularMoviesRepository.popularMoviesLiveData.value

        val movie = popularMovies!!.movies[1]

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










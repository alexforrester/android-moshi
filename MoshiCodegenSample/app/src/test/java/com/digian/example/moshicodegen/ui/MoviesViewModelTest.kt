package com.digian.example.moshicodegen.ui

import androidx.lifecycle.Observer
import com.digian.example.moshicodegen.InstantExecutorExtension
import com.digian.example.moshicodegen.MoviesLifeCycleOwner
import com.digian.example.moshicodegen.data.ASSET_BASE_PATH
import com.digian.example.moshicodegen.data.Movie
import com.digian.example.moshicodegen.data.PopularMoviesRepository
import com.digian.example.moshicodegen.data.PopularMoviesRepositoryImpl
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by Alex Forrester on 2019-04-24.
 */
@ExtendWith(InstantExecutorExtension::class)
internal class MoviesViewModelTest {

    private val moviesViewModel: MoviesViewModel = object : MoviesViewModel(mockk()) {

        override fun getRepository() :  PopularMoviesRepository = object :
            PopularMoviesRepositoryImpl(mockk()) {

            override fun getInputStreamForJsonFile(fileName: String): InputStream {
                return FileInputStream(ASSET_BASE_PATH + fileName)
            }
        }
    }

    @Test
    fun `test observer of getMovies call is called with latest LiveData`() {

        val observer = mockk<Observer<List<Movie>>>()
        every { observer.onChanged(any()) } just Runs

        moviesViewModel.getMovies().observe(MoviesLifeCycleOwner(), observer)

        verify { observer.onChanged(any()) }
        verify {
            observer.onChanged(match { it is List<Movie> })
        }
        verify {
            observer.onChanged(match { it.size == 20 })
        }

        confirmVerified(observer)
    }
}
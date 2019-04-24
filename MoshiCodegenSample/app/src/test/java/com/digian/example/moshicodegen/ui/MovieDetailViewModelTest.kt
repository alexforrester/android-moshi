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

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by Alex Forrester on 2019-04-24.
 */
@ExtendWith(InstantExecutorExtension::class)
internal class MovieDetailViewModelTest {

    private val moviesDetailViewModel: MovieDetailViewModel = object : MovieDetailViewModel(mockk()) {

        override fun getRepository() : PopularMoviesRepository = object :
            PopularMoviesRepositoryImpl(mockk()) {

            override fun getInputStreamForJsonFile(fileName: String): InputStream {
                return FileInputStream(ASSET_BASE_PATH + fileName)
            }
        }
    }

    @Test
    fun `test updates to repository livedata are passed through to viewmodel and movie detail returned when valid movie id passed in`() {

        val observer = mockk<Observer<Movie>>()
        every{ observer.onChanged(any()) } just Runs

        moviesDetailViewModel.getMovie(278).observe(MoviesLifeCycleOwner(), observer)

        verify { observer.onChanged(any()) }
        verify { observer.onChanged(ofType(Movie::class))}
        verify { observer.onChanged(match { it.title == "The Shawshank Redemption" })}

        confirmVerified(observer)
    }

    @Test
    fun `test updates to repository livedata are passed through to viewmodel and movie detail not returned when invalid movie id passed in`() {

        val observer = mockk<Observer<Movie>>()
        every{ observer.onChanged(any()) } just Runs

        //Verifying observer called when no movie found
        moviesDetailViewModel.getMovie(UNKNOWN_MOVIE_ID).observe(MoviesLifeCycleOwner(), observer)

        verify { observer.onChanged(any()) }
        verify { observer.onChanged(isNull())}

        confirmVerified(observer)
    }




}
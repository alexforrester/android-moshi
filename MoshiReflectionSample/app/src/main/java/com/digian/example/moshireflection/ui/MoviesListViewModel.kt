package com.digian.example.moshireflection.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.digian.example.moshireflection.data.Movie
import com.digian.example.moshireflection.data.PopularMoviesRepository
import com.digian.example.moshireflection.data.PopularMoviesRepositoryImpl


/**
 * Created by Alex Forrester on 23/04/2019.
 */
open class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val popularMoviesRepository: PopularMoviesRepository = getRepository()

    fun getMovies() : LiveData<List<Movie>> {
        return popularMoviesRepository.getMovies()
    }

    internal open fun getRepository() : PopularMoviesRepository {
        return PopularMoviesRepositoryImpl(getApplication())
    }
}
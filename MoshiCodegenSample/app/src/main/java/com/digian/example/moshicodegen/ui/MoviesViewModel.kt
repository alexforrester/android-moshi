package com.digian.example.moshicodegen.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.digian.example.moshicodegen.data.PopularMoviesRepository
import com.digian.example.moshicodegen.data.PopularMoviesRepositoryImpl
import androidx.lifecycle.LiveData
import com.digian.example.moshicodegen.data.Movie


/**
 * Created by Alex Forrester on 23/04/2019.
 */
class MoviesViewModel (application: Application) : AndroidViewModel(application) {

    private val movies: LiveData<List<Movie>>? = null

//    fun init(userId: Int) {
//        if (this.user != null) {
//            // ViewModel is created on a per-Fragment basis, so the userId
//            // doesn't change.
//            return
//        }
//        user = userRepo.getUser(userId)
//    }

    private val popularMoviesRepository: PopularMoviesRepository = PopularMoviesRepositoryImpl(application)
    fun getMovies() = popularMoviesRepository.getMovies()
}